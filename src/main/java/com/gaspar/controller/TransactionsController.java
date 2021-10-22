package com.gaspar.controller;

import com.gaspar.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/transactions")
@Slf4j
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService service;


    @GetMapping(path = "/books/{id}")
    ResponseEntity<Map<Object, Object>> getTransactions(@PathVariable("id") Integer id){
        Map<Object, Object> trasactionsByBook= new HashMap<>();
        try {
            trasactionsByBook = service.getTrasactionsByBook(id);
        }catch (Exception e){
            log.info(""+e);
        }
        return new ResponseEntity<>(trasactionsByBook, HttpStatus.OK);
    }
}
