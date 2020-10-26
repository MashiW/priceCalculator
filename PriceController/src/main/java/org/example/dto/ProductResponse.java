package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.model.Product;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class ProductResponse {
    Page<Product> content;
}