package org.example;

import org.example.model.Meta;
import org.example.payload.PriceRequest;

public interface PriceService {

    Meta getCarton(String productId);

    double calculatePrice(PriceRequest priceRequest);

}
