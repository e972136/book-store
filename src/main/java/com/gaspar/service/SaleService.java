/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.service;

import com.gaspar.models.Book;
import com.gaspar.repository.BookRepository;
import com.gaspar.repository.SaleRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public Map<String, Object> newSale(Map<String, Object> fields) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Map<String, Object> resp = new HashMap<>();
        Integer bookId = (Integer)fields.get("bookId");
        Optional<Book> findById = bookService.getBook(bookId);
        if(!findById.isPresent()){            
            resp.put("Respuesta", "Id No Existe");
            return resp;
        }
        Book get = findById.get();
        if(!get.getAvailable()){
            resp.put("Respuesta", "Libro no disponible");
            return resp;           
        }
        if(get.getStock()<=0){
            resp.put("Respuesta", "No hay Existencia");
            return resp;           
        }
        get.setStock(get.getStock()-1);
        bookService.update(get.getId(), get);
        resp.put("bookId", get.getId());
        resp.put("customerEmail", "xxx");
        resp.put("price", get.getSalePrice());   
        return resp;
    }
    
}
