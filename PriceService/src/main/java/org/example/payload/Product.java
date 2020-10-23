package org.example.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productId;
    private String productName;
    private Meta meta;

    public static class Meta {
        private String cartonId;
        private int cartonSize;
        private float cartonPrice;
        private float singleUnitPrice;
    }
}
