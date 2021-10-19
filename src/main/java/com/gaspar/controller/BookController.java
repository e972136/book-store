package com.gaspar.controller;

import com.gaspar.models.Book;
import com.gaspar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

@RestController
@Slf4j
@RequestMapping(path = "/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    ResponseEntity<Map<Object, Object>> allBooksPage(@RequestBody Map<String, Object> fields) {
        try {
            Map<Object, Object> allBooksPage = bookService.allBooksPage(fields);
            return new ResponseEntity<>(allBooksPage, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error", e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping
    ResponseEntity<Book> newBook(@RequestBody Book book) {
        try {
            Book save = bookService.save(book);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping(path = "{id}")
    ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book book) {
        log.info("Request to update Book: {}", book);
        try {
            Book update = bookService.update(id, book);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PatchMapping(path = "{id}")
    ResponseEntity<Book> patch(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
        log.info("Request to patch Book: {}", fields);
        try {
            Book book = bookService.patch(id, fields);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id) {
        log.info("Request to delete Book: {}", id);
        try {
            bookService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
