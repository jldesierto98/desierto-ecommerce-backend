package com.desierto.ecommerce.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id){
        super("The product with id: " + id + " is not found in our system.");
    }
}
