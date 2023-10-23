package com.desierto.ecommerce;

import com.desierto.ecommerce.exception.ErrorResponse;
import com.desierto.ecommerce.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex){
        ErrorResponse response = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
