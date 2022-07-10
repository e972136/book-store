/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaspar.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author ds010102
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="likes")
public class Likes implements Serializable {

    @Id
    @GeneratedValue
    Integer id;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "book_id")
//    Book book;

    Integer bookId;
    String customerEmail;

}
