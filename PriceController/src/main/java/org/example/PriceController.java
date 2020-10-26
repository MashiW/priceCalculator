package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ApiResponse;
import org.example.dto.OrderItemTotalPrice;
import org.example.dto.enums.ApiStatus;
import org.example.impl.service.exception.ProductNotFoundException;
import org.example.payload.PriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/price")
//TODO: Add API doc
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService){
        this.priceService = priceService;
    }

    @PostMapping("/cal")
    public ResponseEntity<ApiResponse<OrderItemTotalPrice>> getCalculatedPrice(@RequestBody PriceRequest priceRequest)
            throws ProductNotFoundException {
        OrderItemTotalPrice response = OrderItemTotalPrice.builder()
                .productId(priceRequest.getProductId())
                .grossTotal(priceService.calculatePrice(priceRequest))
                .build();

        return ResponseEntity
                .ok(ApiResponse.<OrderItemTotalPrice>builder()
                        .code(ApiStatus.SUCCESS)
                        .message("Gross price calculation completed.")
                        .payload(response)
                        .build());
    }
}
