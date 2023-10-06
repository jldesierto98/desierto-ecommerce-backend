package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.exception.ProductNotFoundException;
import com.desierto.ecommerce.product.entity.Product;
import com.desierto.ecommerce.product.repository.ProductRepository;
import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.CartResponse;
import com.desierto.ecommerce.product.response.ProductResponse;
import com.desierto.ecommerce.vo.ProductInCartVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductListServiceImpl implements ProductListService {

    private final ProductRepository productRepository;
    private List<ProductInCartVo> productsInCart = new ArrayList<>(); //temporary storage
    CartResponse cartResponse = new CartResponse();
    int totalQuantity = 0;


    @Override
    public List<ProductResponse> getAllProducts(ProductListRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        return productRepository.findProductByCategory_Named(request.getId(), pageable);
    }

    @Override
    public List<ProductResponse> searchProductByKeyword(ProductSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());


        return productRepository.findProductByKeyword_Named(request.getKeyword(), pageable);
    }

    @Override
    public ProductResponse getProductByProductId(Long id) {
        return productRepository.findByProductId_Named(id);
    }

    @Override
    public CartResponse addToCart(Long id) {
        Optional<Product> getProductById = productRepository.findById(id);
        BigDecimal totalPrice;

        if (getProductById.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        Product product = getProductById.get();


        ProductInCartVo productInCart = new ProductInCartVo();
        productInCart.setId(product.getId());
        productInCart.setName(product.getName());
        productInCart.setUnitPrice(product.getUnitPrice());
        productInCart.setQuantityInCart(1);
        productInCart.setSubTotalPrice(product.getUnitPrice());
        productInCart.setImageUrl(product.getImageUrl());

        Map<Long, ProductInCartVo> cartMap = productsInCart.stream()
                .collect(Collectors.toMap(ProductInCartVo::getId, p -> p));

        ProductInCartVo existingProductInCart = cartMap.get(product.getId());
        if (existingProductInCart != null) {
            existingProductInCart.setQuantityInCart(existingProductInCart.getQuantityInCart() + 1);
            existingProductInCart.setSubTotalPrice(existingProductInCart.getUnitPrice()
                    .multiply(BigDecimal.valueOf(existingProductInCart.getQuantityInCart())));
        } else {
            productsInCart.add(productInCart);
        }

        totalPrice = productsInCart.stream()
                .map(ProductInCartVo::getSubTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //count the total quantity of items in cart.
        int quantityAfterIncrement = productsInCart.stream()
                .mapToInt(ProductInCartVo::getQuantityInCart)
                .sum();

        cartResponse.setProducts(productsInCart);
        cartResponse.setTotalPrice(totalPrice);
        cartResponse.setTotalQuantity(quantityAfterIncrement);

        log.info("=======_______ CART STATUS : |TOTAL PRICE = " + cartResponse.getTotalPrice()
                + "| TOTAL QUANTITY = " + cartResponse.getTotalQuantity());

        return cartResponse;
    }

    @Override
    public CartResponse decrementQuantity(Long id) {

        Optional<Product> getProductById = productRepository.findById(id);


        if (getProductById.isEmpty()) {
            throw new ProductNotFoundException(id);
        }


        for(ProductInCartVo tempProductInCart : productsInCart){

            if(Objects.equals(tempProductInCart.getId(), id)){


                //if the quantity reaches zero, do not allow negative value.
                if(tempProductInCart.getQuantityInCart() <= 0){
                    tempProductInCart.setQuantityInCart(0);
                    tempProductInCart.setSubTotalPrice(tempProductInCart.getUnitPrice()
                            .multiply(BigDecimal.valueOf(tempProductInCart.getQuantityInCart())));


                }else{

                    tempProductInCart.setQuantityInCart(tempProductInCart.getQuantityInCart() - 1);
                    tempProductInCart.setSubTotalPrice(tempProductInCart.getUnitPrice()
                            .multiply(BigDecimal.valueOf(tempProductInCart.getQuantityInCart())));

                }




            }


        }




        BigDecimal totalPrice  = productsInCart.stream()
                .map(ProductInCartVo::getSubTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int quantityAfterDecrement = productsInCart.stream()
                .mapToInt(ProductInCartVo::getQuantityInCart)
                .sum();

        cartResponse.setTotalQuantity(quantityAfterDecrement);
        cartResponse.setTotalPrice(totalPrice);

        return cartResponse;
    }

}

