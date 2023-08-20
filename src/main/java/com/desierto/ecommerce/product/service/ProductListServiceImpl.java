package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.exception.ProductNotFoundException;
import com.desierto.ecommerce.product.entity.Product;
import com.desierto.ecommerce.product.repository.ProductRepository;
import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.AddToCartResponse;
import com.desierto.ecommerce.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductListServiceImpl implements ProductListService {

    private final ProductRepository productRepository;
    private List<Product> productsInCart = new ArrayList<>();

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
    public AddToCartResponse addToCart(Long id) {

        Optional<Product> getProductById = productRepository.findById(id);
        BigDecimal totalPrice = BigDecimal.ZERO;
        AddToCartResponse response = new AddToCartResponse();

        //If product is not found, throw this custom error.
        if (getProductById.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        Product product = getProductById.get();

        //Add the product to Array List. (Temporary Storage - In memory)
        productsInCart.add(product);
        response.setProducts(productsInCart);

        //Loop the products in cart and calculate the sum of all product's unit price.
        for(Product tempProduct : response.getProducts()){
            totalPrice = totalPrice.add(tempProduct.getUnitPrice());
        }

        response.setQuantity(productsInCart.size());
        response.setTotalPrice(totalPrice);
        return response;
    }

}
