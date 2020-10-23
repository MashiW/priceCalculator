package org.example.impl;

import org.example.ProductRepository;
import org.example.ProductService;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<org.example.model.Product> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAllProducts(pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @Override
    public Product getProduct(String productId) throws Exception {
        Optional<Product> productOptional = productRepository.findProductById(productId);
        if(productOptional.isEmpty()){
            throw new Exception();
        }
        return productOptional.get();
    }
}
