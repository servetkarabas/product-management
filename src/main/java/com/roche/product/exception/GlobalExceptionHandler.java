package com.roche.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Map<String, Object>> handleProductNotFoundException(ProductNotFoundException ex,
                                                                                    WebRequest request) {
        return returnResponse("product doesnt exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, WebRequest request) {
        return returnResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> returnResponse(String message, HttpStatus httpStatus) {
        Map<String, Object> error = new LinkedHashMap<String, Object>();
        error.put("timestamp", new Date());
        error.put("message", message);
        return new ResponseEntity<>(error, httpStatus);
    }
}