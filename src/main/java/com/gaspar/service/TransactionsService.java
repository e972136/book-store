package com.gaspar.service;

import com.gaspar.models.Book;
import com.gaspar.models.Sale;
import com.gaspar.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionsService {

    private final SaleService saleService;
    private final BookService bookService;

    public Map<Object, Object> getTrasactionsByBook(Integer id) {
        Map<Object,Object> respuesta = new LinkedHashMap<>();
        Optional<Book> oBook = bookService.getBook(id);
        if(!oBook.isPresent()){
            respuesta.put("Error","Libro no existe");
            return respuesta;
        }
        Book book = oBook.get();
        List<Sale> booksInfo = saleService.getBooksInfo(id);
        Double totalRevenue = booksInfo.stream().map(b -> b.getPrice()).reduce(0.0, (sub, ele) -> sub + ele);
        Set<LocalDateTime> saleDates = booksInfo.stream().map(b->b.getDateOfSale()).collect(Collectors.toSet());
        Set<String> customers = booksInfo.stream().map(b->b.getCustomerEmail()).collect(Collectors.toSet());

        respuesta.put("bookId",id);
        respuesta.put("sales",saleDates);
        respuesta.put("totalRevenue",totalRevenue);
        respuesta.put("customers",customers);

        return respuesta;
    }

}
