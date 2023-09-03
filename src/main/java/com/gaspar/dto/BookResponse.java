package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Override
    public String toString(){
        return title+","+description+","+stock+","+salePrice+","+available;
    }

}
