package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;

    @Min(value=0)
    private Integer stock;
    @Min(value=0)
    private Double salePrice;
}
