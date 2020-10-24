package org.example.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productId;
    private String productName;
    private Meta meta;
    private float unitPrice;

    public static class Meta {
        private int cartonSize;
        private float cartonPrice;
    }
}
