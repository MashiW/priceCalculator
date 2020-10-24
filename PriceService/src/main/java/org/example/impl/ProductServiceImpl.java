package org.example.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.ProductRepository;
import org.example.ProductService;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProducts(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return productRepository.findAll(pageRequest);
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
