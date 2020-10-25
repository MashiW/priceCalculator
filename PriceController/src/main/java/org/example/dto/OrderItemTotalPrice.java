package org.example.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemTotalPrice {
    private String productId;
    private double grossTotal;
}