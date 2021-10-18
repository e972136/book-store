/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
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
@Table(name="sale")
public class Sale  implements Serializable{
   @Id
   @GeneratedValue
   Integer id;
   Integer bookId;
   String customerEmail;
   Double price;
}
