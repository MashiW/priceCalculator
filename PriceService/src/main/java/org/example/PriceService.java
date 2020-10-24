package org.example;

import org.example.payload.PriceRequest;

public interface PriceService {

    double calculatePrice(PriceRequest priceRequest);

}
