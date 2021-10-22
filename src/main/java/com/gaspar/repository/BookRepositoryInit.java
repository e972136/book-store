/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.repository;

import com.gaspar.models.Book;
import java.util.ArrayList;
import java.util.List;
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
        List<Book> l = new ArrayList<>();
        l.add(new Book(1,"Orgullo y prejuicio","de Jane Austen",2,10.5,true,null,null));
        l.add(new Book(2,"Un mundo feliz","de Aldous Huxley",10,13.5,true,null,null));
        l.add(new Book(3,"1984","de George Orwell",1,11.5,false,null,null));
        repository.saveAll(l);        
    }    
}

