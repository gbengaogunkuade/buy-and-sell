package com.ogunkuade.address.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {



    //_____EXCEPTION HANDLER FOR VALIDATION_______
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorList = new ArrayList<>();
        for(ObjectError error : methodArgumentNotValidException.getBindingResult().getAllErrors()) {
            errorList.add(error.getDefaultMessage());
        }
        ValidationResponse validationResponse = new ValidationResponse(new Date(),"VALIDATION EXCEPTION", errorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
    }


    //_______EXCEPTION HANDLER FOR AddressNotFoundException_________
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleAddressNotFoundException(AddressNotFoundException addressNotFoundException){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "ADDRESS NOT FOUND", addressNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }



    //__________EXCEPTION HANDLER FOR OTHER Exceptions_________
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "EXCEPTION", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }


}
