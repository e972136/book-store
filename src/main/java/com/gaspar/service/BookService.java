package com.gaspar.service;

import com.gaspar.models.Book;
import com.gaspar.repository.BookRepository;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ReflectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> todosLosLibros(){
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

    public Optional<Book> getBook(Integer id){
        return bookRepository.findById(id);
    }
    
    @Transactional
    public Book patch(Integer id, Map<Object, Object> fields) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Book bookObtain = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("Id no existe"));
        //System.err.println(" encontrado: "+bookObtain);
        if(bookObtain!=null){
            fields.forEach((key,value)->{
                Field findField = ReflectionUtils.findField(Book.class, key.toString());
                findField.setAccessible(true);
                ReflectionUtils.setField(findField, bookObtain, value);
            });            
            System.err.println(" actualizado: "+bookObtain);
            
        }
        return bookObtain;
    }

    public Map<Object,Object> allBooksPage(Map<String, Object> fields) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       Integer page = 0;
       Integer size = 2;
       String pageStr = (String)fields.getOrDefault("page", null);
       if(pageStr!=null){
           page = Integer.valueOf(pageStr)-1;
       }
       String sizeStr = (String)fields.getOrDefault("size", null);
       if(sizeStr!=null){
           size = Integer.valueOf(sizeStr);
       }
       PageRequest pageRequest = PageRequest.of(page, size);
       Page<Book> findAll = bookRepository.findAll(pageRequest);
       
       Map<Object,Object> respuesta = new LinkedHashMap<>();       
       
       respuesta.put("content", findAll.getContent());
       respuesta.put("size", size);
       respuesta.put("numberOfElements", findAll.getNumberOfElements());
       respuesta.put("totalElements", findAll.getTotalElements());
       respuesta.put("totalPages", findAll.getTotalPages());
       respuesta.put("number", findAll.getNumber());
       return respuesta;
    }
    
    
}
