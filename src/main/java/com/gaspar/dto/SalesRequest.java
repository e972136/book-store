package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesRequest {
    @NotNull
    Integer bookId;
//    @Email
    String customerEmail;
}
