/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.service;

import com.gaspar.dto.LikeResponse;
import com.gaspar.dto.TransactionDto;
import com.gaspar.dto.Utils;
import com.gaspar.exception.GeneralExeption;
import com.gaspar.models.Book;
import com.gaspar.models.Likes;
import com.gaspar.repository.LikesRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ds010102
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LikesService {

    private final LikesRepository repository;
    private final BookService bookService;
    
    @Transactional
    public LikeResponse post(TransactionDto transactionDto) {
        Book book = bookService.getBook(transactionDto.getBookId()).orElse(null);
        if(book == null){
            System.err.println("Libro no existe");
            throw new GeneralExeption("Libro no existe", HttpStatus.BAD_REQUEST);
        }

        if(book.getAvailable()==false){
            System.err.println("Libro no existe");
            throw new GeneralExeption("Libro no disponible", HttpStatus.BAD_REQUEST);
        }

        if(book.getStock()<1){
            System.err.println("Libro no existe");
            throw new GeneralExeption("Libro sin existencia", HttpStatus.BAD_REQUEST);
        }
        Likes likes = Likes.builder()
                .bookId(transactionDto.getBookId())
                .customerEmail(transactionDto.getCustomerEmail()).build();
        repository.save(likes);

        List<Likes> bybookId = repository.findBybookId(transactionDto.getBookId());
        List<String> collect = bybookId.stream().map(r -> r.getCustomerEmail()).collect(Collectors.toList());

        return LikeResponse.of(book.getId(),collect,bybookId.size());
    }


}
