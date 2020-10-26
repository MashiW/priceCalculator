package org.example;

import org.example.impl.PriceServiceImpl;
import org.example.impl.service.exception.ProductNotFoundException;
import org.example.model.Product;
import org.example.model.ProductMeta;
import org.example.payload.PriceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PriceServiceImpl.class, ProductRepository.class})
public class PriceServiceTest {
    @Autowired
    private PriceServiceImpl priceService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void whenCalculatePrice_22Items() throws ProductNotFoundException{
        Product product = createMockProduct();

        when(productRepository.findByProductId(anyString()))
                .thenReturn(Optional.of(product));

        PriceRequest request = new PriceRequest();
        request.setProductId("P00001");
        request.setItemCount(22);

        double expectedPrice = 197.75;
        double resultPrice = priceService.calculatePrice(request);

        assertEquals(expectedPrice, resultPrice, "MISMATCH: Expected total price does not match the result.");
    }

    @Test
    public void whenCalculatePrice_20Items() throws ProductNotFoundException {
        Product product = createMockProduct();

        when(productRepository.findByProductId(anyString()))
                .thenReturn(Optional.of(product));

        PriceRequest request = new PriceRequest();
        request.setProductId("P000001");
        request.setItemCount(60);

        double expectedPrice = 472.5;
        double resultPrice = priceService.calculatePrice(request);

        assertEquals(expectedPrice, resultPrice, "MISMATCH: Expected total price does not match the result.");
    }

    @Test
    public void whenCalculatePrice_18Items() throws ProductNotFoundException {
        Product product = createMockProduct();

        when(productRepository.findByProductId(anyString()))
                .thenReturn(Optional.of(product));

        PriceRequest request = new PriceRequest();
        request.setProductId("P000001");
        request.setItemCount(18);

        double expectedPrice = 204.75;
        double resultPrice = priceService.calculatePrice(request);

        assertEquals(expectedPrice, resultPrice, "MISMATCH: Expected total price does not match the result.");
    }

    private Product createMockProduct() {
        ProductMeta productMeta = new ProductMeta();
        productMeta.setCartonSize(20);
        productMeta.setCartonPrice(175.0f);

        Product product = new Product();
        product.setId("21212133");
        product.setProductId("P00001");
        product.setProductName("Penguin Ear");
        product.setProductMeta(productMeta);
        return product;
    }
}
