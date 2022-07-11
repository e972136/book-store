package com.gaspar.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class TransactionsResponse {
    Integer bookId;
    Set<LocalDateTime> sales;
    Double totalRevenue;
    Set<String> customers;
}
