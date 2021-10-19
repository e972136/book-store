/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.controller;

import com.gaspar.models.Likes;
import com.gaspar.service.LikesService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ds010102
 */
@RestController
@Slf4j
@RequestMapping(path="/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService service;
 
    @PostMapping
    ResponseEntity<Map<String,Object>> post(@RequestBody Map<String,Object> fields){
        try {
            Map<String,Object> save = service.post(fields);
        return new ResponseEntity<>(save,HttpStatus.CREATED);
        } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }        
    }    
}
