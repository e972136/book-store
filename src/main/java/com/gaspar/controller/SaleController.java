/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.controller;

import com.gaspar.service.SaleService;
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
@RequestMapping(path="/sales")
@Slf4j
@RequiredArgsConstructor
public class SaleController {
    private final SaleService service;
    
    @PostMapping
    ResponseEntity<Map<String,Object>> newSale(@RequestBody Map<String,Object> fields){
        try {
            Map<String, Object> newSale = service.newSale(fields);
        return new ResponseEntity<>(newSale,HttpStatus.CREATED);
        } catch (Exception e) {
            log.info("Error", e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        
    }
}
