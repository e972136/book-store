package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private Integer id;
    private String title;
    private String description;
    private Integer stock;
    private Double salePrice;
    private Boolean available;

//    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Likes like;

    @Override
    public String toString(){
        return title+","+description+","+stock+","+salePrice+","+available;
    }

}
