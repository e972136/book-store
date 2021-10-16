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
    
    @Override
    public String toString(){
        return title+","+description+","+stock+","+salePrice+","+available;
    }

    public void set(Book book) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
       if(book.title!=null)
        this.title = book.title;
       
       if(book.description!=null)
        this.description = book.description;
       
       if(book.stock!=null)
        this.stock = book.stock;
       
       if(book.salePrice!=null)
        this.salePrice = book.salePrice;
       
       if(book.available!=null)
        this.available = book.available;
    }
    
}
