/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

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
   @GeneratedValue
   Integer id;

   Integer bookId;
   String customerEmail;
   Double price;
   LocalDateTime dateOfSale;

//   @ManyToOne(optional = false)
//   @JoinColumn(name = "bookId",nullable = false)
//   private Book book;
}
