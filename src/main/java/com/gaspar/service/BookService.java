package com.gaspar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gaspar.dto.BookRequest;
import com.gaspar.dto.BookRequestPatch;
import com.gaspar.dto.BookPageResponse;
import com.gaspar.dto.BookResponse;
import com.gaspar.models.Book;
import com.gaspar.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ReflectionUtils;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    public ResponseEntity<BookResponse> save(BookRequest bookDto) {
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .stock(bookDto.getStock())
                .salePrice(bookDto.getSalePrice())
                .available(true)
                .build();
        Book save = bookRepository.save(book);
        BookResponse bookResponse = null;
        try {
            bookResponse = objectMapper.readValue(objectMapper.writeValueAsString(save), BookResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<BookResponse> update(Integer id, BookRequest book) {
        Book bookObtain = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("Id no existe"));
        if(nonNull(book.getDescription())){
            bookObtain.setDescription(book.getDescription());
        }
        if(nonNull(book.getAvailable())){
            bookObtain.setAvailable(book.getAvailable());
        }
        bookObtain.setTitle(book.getTitle());
        bookObtain.setStock(book.getStock());
        bookObtain.setSalePrice(book.getSalePrice());

        BookResponse bookResponse = null;
        try {
            bookResponse = objectMapper.readValue(objectMapper.writeValueAsString(bookObtain), BookResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getBook(Integer id) {
        return bookRepository.findById(id);
    }

    /**
     *
     * @param id
     * @param fields
     * @return
     */
    @Transactional
    public ResponseEntity<BookResponse> patch(Integer id, BookRequestPatch fields) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Book bookObtain = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("Id no existe"));
        if(nonNull(fields.getTitle())){
            bookObtain.setTitle(fields.getTitle());
        }
        if(nonNull(fields.getDescription())){
            bookObtain.setDescription(fields.getDescription());
        }
        if(nonNull(fields.getStock())){
            bookObtain.setStock(fields.getStock());
        }
        if(nonNull(fields.getSalePrice())){
            bookObtain.setSalePrice(fields.getSalePrice());
        }
        if(nonNull(fields.getAvailable())){
            bookObtain.setAvailable(fields.getAvailable());
        }
//            fields.forEach((key, value) -> {
//                if(key.equals("id")) return;
//                Field findField = ReflectionUtils.findField(Book.class, key.toString());
//                findField.setAccessible(true);
//                ReflectionUtils.setField(findField, bookObtain, value);
//            });
            System.err.println(" actualizado: " + bookObtain);

        BookResponse bookResponse = null;
        try {
            bookResponse = objectMapper.readValue(objectMapper.writeValueAsString(bookObtain), BookResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return  new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    public BookPageResponse allBooksPage(boolean unavailable,
                                         Integer page,
                                         Integer size,
                                         String sortStr
    ) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (page != null) {
            page = page - 1;
        }
        if (page < 0) {
            page = 0;
        }

        if (size <= 0) {
            size = 12;
        }

        Field findField = ReflectionUtils.findField(Book.class, sortStr);
        if (findField == null) {
            System.err.println("Columna " + sortStr + " no existe");
             sortStr = "title";
        }

        Sort sortBy = Sort.by(sortStr);
        PageRequest pageRequest = PageRequest.of(page, size, sortBy);
        Page<Book> findAll = bookRepository.findAll(pageRequest);


        BookPageResponse respuesta = new BookPageResponse();

        Boolean showUnavaible = unavailable;

        if (showUnavaible) {
            System.err.println("muestra todo");
            respuesta.setContent(findAll.getContent());
        } else {
            System.err.println("muestra restringido");
            respuesta.setContent(findAll.getContent().stream().filter(f -> f.getAvailable()).collect(Collectors.toList()));
        }


        respuesta.setSize(size);
        respuesta.setNumberOfElements(findAll.getNumberOfElements());
        respuesta.setTotalElements(findAll.getTotalElements());
        respuesta.setTotalPages(findAll.getTotalPages());
        respuesta.setNumber(findAll.getNumber()+1);

        return respuesta;
    }


}
