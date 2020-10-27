package org.example.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemTotalPrice {
    private final String productId;
    private final long grossTotal;
}