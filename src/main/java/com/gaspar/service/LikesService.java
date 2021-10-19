/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.service;

import com.gaspar.models.Book;
import com.gaspar.models.Likes;
import com.gaspar.models.Sale;
import com.gaspar.repository.LikesRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void update(Integer id, String customerEmail) {       
        Likes obtein = repository.findById(id).orElseThrow(() -> new IllegalStateException("Id no existe"));
        if(!obtein.getCustomerEmail().toLowerCase().contains(customerEmail.toLowerCase())){
            obtein.setCustomerEmail(obtein.getCustomerEmail()+";"+customerEmail);  
            obtein.setLikes(obtein.getLikes()+1);
        }        
    }
    
    @Transactional
    public Map<String, Object> post(Map<String, Object> fields) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Integer bookId =  (Integer)fields.getOrDefault("bookId", null);
        if(bookId==null){
            respuesta.put("Error", "No esta el campo bookId");
            return respuesta;
        }
      
        Optional<Book> book = bookService.getBook(bookId);
        if(!book.isPresent()){
            respuesta.put("Error", "No esta el libro "+bookId);
            return respuesta;
        }
        
        String customerEmail =  (String)fields.getOrDefault("customerEmail", null);
        if(customerEmail==null){
            respuesta.put("Error", "No esta el campo customerEmail");
            return respuesta;
        }       
        respuesta.put("bookId", bookId);        
        
        Optional<Likes> findById = repository.findById(bookId);
        if(findById.isPresent()){           
            Likes like = findById.get();
            update(like.getBookId(),customerEmail);
            respuesta.put("likes", like.getLikes());    
            String[] split = like.getCustomerEmail().split(";");
            List<String> l = Arrays.asList(split);
            respuesta.put("customers",l);
        }else{        

            Likes save = repository.save(new Likes(bookId,customerEmail,1));
            respuesta.put("likes", save.getLikes());   
            List<String> l = new ArrayList<>();
            l.add(customerEmail);
            respuesta.put("customers",l);
        }
        
        return respuesta;        
    }
    
}
