package com.gaspar.service;

import com.gaspar.dto.TransactionsResponse;
import com.gaspar.exception.GeneralExeption;
import com.gaspar.models.Book;
import com.gaspar.models.Sale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionsService {

    private final SaleService saleService;
    private final BookService bookService;

    public TransactionsResponse getTrasactionsByBook(Integer id) {
        TransactionsResponse respuesta = new TransactionsResponse();
        Book book = bookService.getBook(id).orElse(null);
        if(book==null){
            log.info("Libro no existe");
            throw new GeneralExeption("Libro no existe", HttpStatus.BAD_REQUEST);
        }
        List<Sale> booksInfo = saleService.getBooksInfo(id);
        Double totalRevenue = booksInfo.stream().map(Sale::getPrice).reduce(0.0, (sub, ele) -> sub + ele);
        Set<LocalDate> saleDates = booksInfo.stream().map(Sale::getDateOfSale).collect(Collectors.toSet());
        Set<String> customers = booksInfo.stream().map(Sale::getCustomerEmail).collect(Collectors.toSet());

        respuesta.setBookId(id);
        respuesta.setSales(saleDates);
        respuesta.setTotalRevenue(totalRevenue);
        respuesta.setCustomers(customers);

        return respuesta;
    }

}
