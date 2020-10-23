package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllProducts(@RequestParam(value = "offset") Integer offset,
                                                @RequestParam(value= "limit") Integer limit){
        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<Product> products = productService.getAllProducts(pageRequest);

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/single")
    public ResponseEntity<String> getProduct(@RequestParam(value = "productId") String productId) throws Exception {
        Product product = productService.getProduct(productId);
        return ResponseEntity.ok("Success");
    }

}
