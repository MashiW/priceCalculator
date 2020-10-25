package org.example.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.PriceService;
import org.example.ProductRepository;
import org.example.impl.service.exception.ProductNotFoundException;
import org.example.model.ProductMeta;
import org.example.model.Product;
import org.example.payload.PriceRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    // TODO: Ideally the following should be retrieved from database
    private static final double DISCOUNT_FACTOR = 0.9;
    private static final double LOOSE_PRICE_ADDITION_FACTOR = 1.3;
    private static final int DISCOUNT_ELIGIBILITY_MARGIN = 3;

    private final ProductRepository productRepository;

    public PriceServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(String productId) throws ProductNotFoundException {
        return productRepository
                .findProductById(productId)
                .orElseThrow(()-> new ProductNotFoundException(productId));
    }

    @Override
    public double calculatePrice(PriceRequest priceRequest) throws ProductNotFoundException {

        ProductMeta carton = getProduct(priceRequest.getProductId()).getProductMeta();
        int cartonSize = carton.getCartonSize();
        int orderedItemCount = priceRequest.getItemCount();
        float cartonPrice = carton.getCartonPrice();
        double totalPrice;

        if (orderedItemCount % cartonSize == 0) {
            int cartonsQty = orderedItemCount / cartonSize;

            totalPrice = cartonPrice * cartonsQty;

            if (cartonsQty >= DISCOUNT_ELIGIBILITY_MARGIN) {
                totalPrice *= DISCOUNT_FACTOR;
            }
        }
        else if(orderedItemCount < cartonSize) {
            return orderedItemCount * calculateLooseItemsPrice(cartonSize, cartonPrice);
        }
        else {
            int retailItemsQty = orderedItemCount % cartonSize;
            int cartonsQty = (orderedItemCount - retailItemsQty) / cartonSize;

            double totalForLooseItems = retailItemsQty * calculateLooseItemsPrice(cartonSize, cartonPrice);

            double totalForCartons = cartonPrice * cartonsQty;

            if (cartonsQty >= DISCOUNT_ELIGIBILITY_MARGIN) {
                totalForCartons *= DISCOUNT_FACTOR;
            }

            totalPrice = totalForLooseItems + totalForCartons;
        }

        return totalPrice;
    }

    private double calculateLooseItemsPrice(int cartonSize, float cartonPrice) {
        return (cartonPrice / cartonSize) * LOOSE_PRICE_ADDITION_FACTOR;
    }
}
