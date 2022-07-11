package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    @NotNull
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Min(value=0)
    private Integer stock;

    @NotNull
    @Min(value=0)
    private Double salePrice;

    private Boolean available;
}
