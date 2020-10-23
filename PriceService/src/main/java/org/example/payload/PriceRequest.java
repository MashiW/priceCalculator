package org.example.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRequest {
    private String productId;
    private int itemCount;
}
