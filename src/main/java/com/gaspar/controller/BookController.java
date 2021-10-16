package com.gaspar.controller;

import com.gaspar.models.Book;
import com.gaspar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path="/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    List<Book> allBooks(){
        System.err.println(" allBooks: ");
        return bookService.todosLosLibros();
    }

    @PostMapping
    Book newBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping(path = "{id}")
    Book updateBook(@PathVariable("id") Integer id, @RequestBody Book book){
        return bookService.update(id,book);
    }
    
    @PatchMapping(path = "{id}")
    Book patch(@PathVariable Integer id, @RequestBody Map<Object,Object> fields){
        System.err.println(" patch: "+id);
        return bookService.patch(id,fields);
    }

    @DeleteMapping(path = "{id}")
    void deleteBook(@PathVariable("id") Integer id){
        bookService.delete(id);
    }
}
