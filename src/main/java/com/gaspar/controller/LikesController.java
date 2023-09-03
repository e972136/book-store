/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.controller;

import com.gaspar.dto.LikeRequest;
import com.gaspar.dto.LikeResponse;
import com.gaspar.dto.SalesRequest;
import com.gaspar.service.LikesService;

import java.io.Serializable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 * @author ds010102
 */
@RestController
@Slf4j
@RequestMapping(path="/likes")
@RequiredArgsConstructor
public class LikesController implements Serializable {
    private final LikesService service;
 
    @PostMapping
    ResponseEntity<LikeResponse> post(@Valid @RequestBody LikeRequest likeRequest){
        try {
//            LikeResponse save = service.post(likeRequest);

            return service.post(likeRequest);
        }
        catch (RuntimeException e){
            log.info(""+e);
            throw e;
        }
        catch (Exception e) {
            log.info("Error", e);
             return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }        
    }    
}
