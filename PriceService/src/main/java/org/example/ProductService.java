package org.example;

import org.example.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {
    Page<org.example.model.Product> getAllProducts(PageRequest pageRequest);

    Product getProduct(String productId) throws Exception;
}
