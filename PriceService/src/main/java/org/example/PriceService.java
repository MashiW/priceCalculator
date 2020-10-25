package org.example;

import org.example.impl.service.exception.ProductNotFoundException;
import org.example.model.Product;
import org.example.payload.PriceRequest;

public interface PriceService {

    Product getProduct(String productId) throws ProductNotFoundException;

    double calculatePrice(PriceRequest priceRequest) throws ProductNotFoundException;

}
