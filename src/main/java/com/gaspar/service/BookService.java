package com.gaspar.service;

import com.gaspar.models.Book;
import com.gaspar.repository.BookRepository;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ReflectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> todosLosLibros() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Integer id, Book book) {
        Book bookObtain = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("Id no existe"));
        bookObtain.set(book);
        return bookObtain;
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getBook(Integer id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book patch(Integer id, Map<Object, Object> fields) {        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Book bookObtain = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("Id no existe"));
        //System.err.println(" encontrado: "+bookObtain);
        if (bookObtain != null) {
            fields.forEach((key, value) -> {
                if(key.equals("id")) return;
                Field findField = ReflectionUtils.findField(Book.class, key.toString());
                findField.setAccessible(true);
                ReflectionUtils.setField(findField, bookObtain, value);
            });
            System.err.println(" actualizado: " + bookObtain);

        }
        return bookObtain;
    }

    public Map<Object, Object> allBooksPage(Map<String, Object> fields) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Integer page = 0;
        Integer size = 2;
        page = (Integer) fields.getOrDefault("page", null);
        if (page != null) {
            page = page - 1;
        }
        if (page < 0) {
            page = 0;
        }
        size = (Integer) fields.getOrDefault("size", null);        

        if (size <= 0) {
            size = 12;
        }

        String msgSorg = null;
        String isSortBy = (String) fields.getOrDefault("sort", null);
        Sort sortBy = Sort.by("title");
        
        if (isSortBy != null) {
            Field findField = ReflectionUtils.findField(Book.class, isSortBy);
            if (findField != null) {
                sortBy = Sort.by(isSortBy);
            } else {
                msgSorg = "Columna " + isSortBy + " no existe";
            }
        }

        PageRequest pageRequest = PageRequest.of(page, size, sortBy);
        Page<Book> findAll = bookRepository.findAll(pageRequest);

        Map<Object, Object> respuesta = new LinkedHashMap<>();

        Boolean showUnavaible = false;
        Object showUnavaibleGet =  fields.getOrDefault("unavailable", null);
        if(showUnavaibleGet!=null)
        {
            try{
                showUnavaible = (Boolean)showUnavaibleGet;
            }catch(Exception e){

            }
        }
        if (showUnavaible) {
            System.err.println("muestra todo");
            respuesta.put("content", findAll.getContent());
        } else {
            System.err.println("muestra restringido");
            respuesta.put("content", findAll.getContent().stream().filter(f -> f.getAvailable()).collect(Collectors.toList()));
        }


        respuesta.put("size", size);
        respuesta.put("numberOfElements", findAll.getNumberOfElements());
        respuesta.put("totalElements", findAll.getTotalElements());
        respuesta.put("totalPages", findAll.getTotalPages());
        respuesta.put("number", findAll.getNumber()+1);
        if (msgSorg != null) {
            respuesta.put("Error", msgSorg);
        }
        return respuesta;
    }


}
