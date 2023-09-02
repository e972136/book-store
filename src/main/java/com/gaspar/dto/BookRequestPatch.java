package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestPatch {

    private String title;
    private String description;
    private Integer stock;
    private Double salePrice;
    private Boolean available;
}
