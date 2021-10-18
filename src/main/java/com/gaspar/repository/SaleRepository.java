/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.repository;

import com.gaspar.models.Book;
import com.gaspar.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ds010102
 */
public interface SaleRepository extends JpaRepository<Sale,Integer>{
    
}
