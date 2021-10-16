package com.gaspar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue
    Integer id;//`: identificador unico del libro

    String title; //`: titulo del libro
    String description; //`: informacion acerca del libro
    Integer stock; //`: cuantos items de este libro estan disponibles
    Double salePrice;//`: cuanto costara el libro
    Boolean available; //`: indicador que muestra si el libro puede ser vendido
}
