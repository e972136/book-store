package com.gaspar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class TransactionsResponse {
    Integer bookId;

    @JsonFormat(pattern="dd-MM-yyyy")
    Set<LocalDate> sales;

    Double totalRevenue;
    Set<String> customers;
}
