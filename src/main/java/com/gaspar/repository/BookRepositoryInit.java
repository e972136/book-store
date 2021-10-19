/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.repository;

import com.gaspar.models.Book;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author ds010102
 */
@Component
@RequiredArgsConstructor
public class BookRepositoryInit {
    
    private final BookRepository repository;
    
    @PostConstruct
    private void postConstruct() {
        Book b = new Book(1,"title","descrip",10,10.5,true);       
        repository.save(b);
    }    
}

