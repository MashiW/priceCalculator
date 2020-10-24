package org.example.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.PriceService;
import org.example.ProductRepository;
import org.example.model.Meta;
import org.example.model.Product;
import org.example.payload.PriceRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    private final ProductRepository productRepository;

    public PriceServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Meta getCarton(String productId) {
        Optional<Product> product = productRepository.findProductById(productId);
        return product.get().getMeta();
    }

    @Override
    public double calculatePrice(PriceRequest priceRequest) {

        Meta carton = getCarton(priceRequest.getProductId());
        int cartonSize = carton.getCartonSize();
        float cartonPrice = carton.getCartonPrice();

        int orderedItemCount = priceRequest.getItemCount();
        double totalPrice = 0;
        int cartonQty =0;
        double discountedPrice = 0.9;
        int singleItemsQty = 0;
        double singleItemGivenPrice = (cartonPrice/cartonSize) * 1.3 ;
        double singleItemCalculatedPrice = 0;
        double totalCartonPrice = 0;

        //only cartons
        if(orderedItemCount%cartonSize ==0 ){
            cartonQty = orderedItemCount/cartonSize;

            if(cartonQty >= 3){
                totalPrice = cartonPrice * discountedPrice * cartonQty;
            }else{
                totalPrice = cartonPrice * cartonQty;
            }
        }
        // both cartons and single units
        else if(orderedItemCount/cartonSize >= 1 &&
                orderedItemCount%cartonSize < cartonSize){

            cartonQty = orderedItemCount/cartonSize;
            singleItemsQty = orderedItemCount%cartonSize;
            singleItemCalculatedPrice = singleItemsQty * singleItemGivenPrice;

            if(cartonQty >= 3){
                totalCartonPrice = cartonPrice * discountedPrice * cartonQty;
                totalPrice = singleItemCalculatedPrice + totalCartonPrice;
            }else{
                totalCartonPrice = cartonPrice * cartonQty;
                totalPrice = totalCartonPrice + singleItemCalculatedPrice;
            }
        }
        // only single units, no cartons
        else if(orderedItemCount%cartonSize < cartonSize){
            singleItemsQty = orderedItemCount%cartonSize;
            totalPrice = singleItemsQty * singleItemGivenPrice;
        }
        return totalPrice;
    }
}
