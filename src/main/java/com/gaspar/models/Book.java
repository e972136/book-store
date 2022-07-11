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

}
