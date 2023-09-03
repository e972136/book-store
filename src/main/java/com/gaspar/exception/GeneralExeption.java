package com.gaspar.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GeneralExeption extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;

    public GeneralExeption(String message,HttpStatus httpStatus) {
        super(message);
//        this.message = "{\"error\" : \""+message+"\"}";
        this.message = "{\n" +
                "\"errorCode\":\""+ httpStatus +"\",\n" +
                "\"error\":\""+message+"\"\n" +
                "}";
        this.httpStatus = httpStatus;
    }

}
