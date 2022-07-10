package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class SaleResponse {
     Integer bookId;
     String customerEmail;
     Double price;
}
