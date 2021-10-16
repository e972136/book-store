package com.gaspar.controller;

import com.gaspar.models.Book;
import com.gaspar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    List<Book> allBooks(){
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

    @DeleteMapping(path = "{id}")
    void deleteBook(@PathVariable("id") Integer id){

    }
}
