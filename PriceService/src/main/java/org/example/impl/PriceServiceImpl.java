package org.example.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.PriceService;
import org.example.payload.PriceRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    @Override
    public double calculatePrice(PriceRequest priceRequest) {
        return priceRequest.getItemCount() * 100.0;
    }
}
