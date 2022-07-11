package com.gaspar.dto;

import com.gaspar.models.Book;
import lombok.Data;

import java.util.List;

@Data
public class BookPageResponse {
    List<Book> content;
    Integer size;
    Integer numberOfElements;
    Long totalElements;
    Integer totalPages;
    Integer number;
}
