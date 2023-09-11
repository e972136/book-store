/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ds010102
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="sale")
public class Sale  implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer id;
   String customerEmail;
   Double price;
   LocalDate dateOfSale;

   @ManyToOne(optional = false)
   @JoinColumn(name = "bookId",nullable = false)
   @JsonBackReference
   private Book book;


}
