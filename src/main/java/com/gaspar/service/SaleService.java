/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.service;

import com.gaspar.exception.GeneralExeption;
import com.gaspar.dto.SalesRequest;
import com.gaspar.dto.SaleResponse;
import com.gaspar.models.Book;
import com.gaspar.models.Sale;
import com.gaspar.repository.SaleRepository;

import java.time.LocalDate;
import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

/**
 *
 * @author ds010102
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SaleService {
    private final SaleRepository repository;
    private final BookService bookService;

    public List<Sale> getBooksInfo(Integer id){
        return repository.findBybookId(id);
    }

    @Transactional
    public SaleResponse newSale(SalesRequest salesRequest) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Book book = bookService.getBook(salesRequest.getBookId()).orElse(null);
        if(isNull(book)){
            log.info("Libro no existe");
            throw new GeneralExeption("Libro no existe", HttpStatus.BAD_REQUEST);
        }

        if(!Boolean.TRUE.equals(book.getAvailable())){
            log.info("Libro no disponible");
            throw new GeneralExeption("Libro no disponible", HttpStatus.NOT_ACCEPTABLE);
        }

        if(book.getStock()<1){
            log.info("Libro sin existencia");
            throw new GeneralExeption("Libro sin existencia", HttpStatus.INSUFFICIENT_STORAGE);
        }

        book.setStock(book.getStock()-1);

        Sale sale = Sale.builder()
                .customerEmail(salesRequest.getCustomerEmail())
                .price(book.getSalePrice())
                .dateOfSale(LocalDate.now())
                .book(book)
                .build();
        Sale save = repository.save(sale);
        return SaleResponse.of(book.getId(),save.getCustomerEmail(),save.getPrice());
    }

}
