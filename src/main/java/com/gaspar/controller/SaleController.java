/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.controller;

import com.gaspar.service.SaleService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SaleController {
    private final SaleService service;
    
    @PostMapping
    Map<String,Object> newSale(@RequestBody Map<String,Object> fields){
        return service.newSale(fields);
    }
}
