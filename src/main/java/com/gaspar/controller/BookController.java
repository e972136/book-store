package com.gaspar.controller;

import com.gaspar.dto.BookRequest;
import com.gaspar.dto.BookRequestPatch;
import com.gaspar.dto.BookPageResponse;
import com.gaspar.dto.BookResponse;
import com.gaspar.models.Book;
import com.gaspar.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(path = "/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private static final String ERROR = "Error ";

    @GetMapping
    ResponseEntity<BookPageResponse> allBooksPage(
            @RequestParam(required = false,defaultValue = "false") boolean unavailable,
            @RequestParam(required = false,defaultValue = "1") Integer page,
            @RequestParam(required = false,defaultValue = "12") Integer size,
            @RequestParam(required = false,defaultValue = "title") String sort
    ) {
        try {
            BookPageResponse allBooksPage = bookService.allBooksPage(unavailable,page,size,sort);
            return new ResponseEntity<>(allBooksPage, HttpStatus.OK);
        }
        catch (RuntimeException e){
            log.info(""+e);
            throw e;
        }
        catch (Exception e) {
            log.info(ERROR, e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping
    ResponseEntity<BookResponse> newBook(
            @Valid @RequestBody BookRequest bookDto,
            BindingResult result) {
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        try {
            return bookService.save(bookDto);
        } catch (Exception e) {
            log.info(ERROR, e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<BookResponse> updateBook(@PathVariable("id") Integer id,
                                    @RequestBody BookRequest book,
                                    BindingResult result
    ) {
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        log.info("Request to update Book: {}", book);
        try {
            return bookService.update(id, book);
        } catch (Exception e) {
            log.info(ERROR, e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PatchMapping(path = "{id}")
    ResponseEntity<BookResponse> patch(@PathVariable Integer id, @RequestBody BookRequestPatch fields) {
        log.info("Request to patch Book: {}", fields);
        try {
            return bookService.patch(id, fields);
        } catch (Exception e) {
            log.info(ERROR, e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id) {
        log.info("Request to delete Book: {}", id);
        try {
            bookService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.info(ERROR, e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    String formatMessage(BindingResult result){
        String collect = result.getFieldErrors().stream().map(err -> {
            return err.getField() + "->" + err.getDefaultMessage();
        }).collect(Collectors.joining(","));
        return collect;
    }
}
