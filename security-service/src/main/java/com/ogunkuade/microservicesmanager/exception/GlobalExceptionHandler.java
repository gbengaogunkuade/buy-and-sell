package com.ogunkuade.microservicesmanager.exception;


import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
    public ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> methodArgErrorList =  new ArrayList<>();
        for(ObjectError error : methodArgumentNotValidException.getBindingResult().getAllErrors()){
            methodArgErrorList.add(error.getDefaultMessage());
        }
        ValidationResponse validationResponse = new ValidationResponse(new Date(), "VALIDATION ERROR", methodArgErrorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
    }



    //_____EXCEPTION HANDLER FOR AccessDeniedException EXCEPTION____
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException accessDeniedException){
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), "ACCESS DENIED EXCEPTION", "Sorry, Access Denied For This Request");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }



    //_____EXCEPTION HANDLER FOR UserNotFoundException EXCEPTION____
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), "USER NOT FOUND EXCEPTION", userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    //_____EXCEPTION HANDLER FOR UserAlreadyExistsException EXCEPTION____
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), "USER ALREADY EXISTS EXCEPTION", userAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }


    //_____EXCEPTION HANDLER FOR UnmatchedPasswordException EXCEPTION____
    @ExceptionHandler(UnmatchedPasswordException.class)
    public ResponseEntity<ExceptionResponse> handleUnmatchedPasswordException(UnmatchedPasswordException unmatchedPasswordException){
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), "UNMATCHED PASSWORD EXCEPTION", unmatchedPasswordException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    //_____EXCEPTION HANDLER FOR UnauthorizedRequestException EXCEPTION____
    @ExceptionHandler(UnauthorizedRequestException.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorizedRequestException(UnauthorizedRequestException unauthorizedRequestException){
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), "UNAUTHORIZED REQUEST", unauthorizedRequestException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    //_____EXCEPTION HANDLER FOR AddressNotFoundException EXCEPTIONS____
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleAddressNotFoundException(AddressNotFoundException addressNotFoundException){
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), "ADDRESS NOT FOUND", addressNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }



    //_____EXCEPTION HANDLER FOR OTHER EXCEPTIONS____
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception){
        ExceptionResponse errorResponse = new ExceptionResponse(new Date(), "EXCEPTION", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }





}
