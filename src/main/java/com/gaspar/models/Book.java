package com.gaspar.models;

import java.io.Serializable;
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
public class Book implements Serializable {

    @Id
    @GeneratedValue
    Integer id;

    String title;
    String description; 
    Integer stock; 
    Double salePrice;
    Boolean available; 
    
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
