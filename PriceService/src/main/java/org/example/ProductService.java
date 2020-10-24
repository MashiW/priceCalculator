package org.example;

import org.example.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getAllProducts(int offset, int limit);

    Product getProduct(String productId) throws Exception;
}
