/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.service;

import com.gaspar.Utils;
import com.gaspar.models.Book;
import com.gaspar.models.Sale;
import com.gaspar.repository.BookRepository;
import com.gaspar.repository.SaleRepository;
import java.time.LocalDate;
import java.util.*;

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

    public List<Sale> getBooksInfo(Integer id){
        return repository.findBybookId(id);
    }

    public Map<String, Object> newSale(Map<String, Object> fields) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Map<String, Object> resp = new HashMap<>();
        Integer bookId = (Integer)fields.getOrDefault("bookId",null);
        if(bookId == null){                    
            resp.put("Error", "No esta el campo bookId");
            return resp;
        }
        
        String customerEmail = (String)fields.getOrDefault("customerEmail",null);
        if(customerEmail==null){
             resp.put("Error", "No esta el campo customerEmail");
            return resp;           
        }
        
        if(!Utils.correoValido(customerEmail)){
            resp.put("Error", "Correo invalido "+customerEmail);
            return resp;
        }
        
        Optional<Book> findById = bookService.getBook(bookId);
        if(!findById.isPresent()){            
            resp.put("Error", "Id No Existe");
            return resp;
        }
        Book book = findById.get();
        
        if(!book.getAvailable()){
            resp.put("Error", "Libro no disponible");
            return resp;           
        }
        if(book.getStock()<=0){
            resp.put("Error", "No hay Existencia");
            return resp;           
        }
        book.setStock(book.getStock()-1);
        bookService.update(book.getId(), book);
        resp.put("bookId", book.getId());
        resp.put("customerEmail", customerEmail);
        resp.put("price", book.getSalePrice());   
        Sale sale = new Sale();
        sale.setBookId(book.getId());
        sale.setCustomerEmail(customerEmail);
        sale.setPrice(book.getSalePrice());
        LocalDate date = LocalDate.now(); 
        sale.setDateOfSale(date.toString());
        repository.save(sale);  

        return resp;
    }
    
}
