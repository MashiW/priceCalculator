package org.example.impl.service.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String productId) {
        super(String.format("No product was found for the ID %s.", productId));
    }
}
