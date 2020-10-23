package org.example;

import org.example.payload.PriceRequest;

public interface PriceService {

    int calculatePrice(PriceRequest priceRequest);

}
