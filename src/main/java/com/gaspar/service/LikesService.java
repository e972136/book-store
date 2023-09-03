/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaspar.dto.LikeRequest;
import com.gaspar.dto.LikeResponse;
import com.gaspar.dto.SalesRequest;
import com.gaspar.exception.GeneralExeption;
import com.gaspar.models.Book;
import com.gaspar.models.Likes;
import com.gaspar.repository.LikesRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LikeResponse> post(LikeRequest likeRequest) {
        Book book = bookService.getBook(likeRequest.getBookId()).orElseThrow(() -> new IllegalStateException("Id no existe"));
        if(book.getAvailable()==false){
            System.err.println("Libro no existe");
            throw new GeneralExeption("Libro no disponible", HttpStatus.BAD_REQUEST);
        }
        Likes likes = Likes.builder()
                .customerEmail(likeRequest.getCustomerEmail())
                .book(book)
                .build();
        repository.save(likes);

        List<Likes> todosLikes = repository.findBybookId(book.getId());

        Set<String> collect = todosLikes
                .stream()
                .map(l -> l.getCustomerEmail())
                .collect(Collectors.toSet());

        LikeResponse likeResponse = LikeResponse.of(book.getId(),collect,todosLikes.size());



        return new ResponseEntity<>(likeResponse,HttpStatus.CREATED);
    }
}
