package com.gaspar.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="book")
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String description;
    private Integer stock;
    private Double salePrice;
    private Boolean available;

//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private List<Sale> sales;


//    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Likes like;

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
