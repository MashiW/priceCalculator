package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.payload.PriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/price")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService){
        this.priceService = priceService;
    }

    @PostMapping("/cal")
    public ResponseEntity<Double> getCalculatedPrice(@RequestBody PriceRequest priceRequest) {
        return ResponseEntity
                .ok(priceService.calculatePrice(priceRequest));
    }
}
