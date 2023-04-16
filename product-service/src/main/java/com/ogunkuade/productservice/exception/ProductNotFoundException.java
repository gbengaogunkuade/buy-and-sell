package com.ogunkuade.productservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {


    private static final Long serialVersionUID = 1L;


    public ProductNotFoundException(String message){
        super(message);
    }


}
