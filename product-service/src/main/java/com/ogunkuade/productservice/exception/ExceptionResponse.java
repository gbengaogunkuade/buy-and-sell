package com.ogunkuade.productservice.exception;


import java.util.Date;

public record ExceptionResponse(Date timestamp, String subject, String message) {


}
