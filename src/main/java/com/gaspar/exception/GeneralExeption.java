package com.gaspar.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GeneralExeption extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public GeneralExeption(String message,HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
