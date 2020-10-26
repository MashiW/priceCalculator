package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ApiResponse;
import org.example.dto.OrderItemTotalPrice;
import org.example.dto.ProductResponse;
import org.example.dto.enums.ApiStatus;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<ApiResponse> getAllProducts(@RequestParam(value = "offset") Integer offset,
                                                        @RequestParam(value= "limit") Integer limit){

        Page<Product> products = productService.getAllProducts(offset, limit);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(ApiStatus.SUCCESS)
                .payload(products)
                .build());
    }

    @GetMapping("/single")
    public ResponseEntity<String> getProduct(@RequestParam(value = "productId") String productId) throws Exception {
        Product product = productService.getProduct(productId);
        return ResponseEntity.ok("Success");
    }

}
