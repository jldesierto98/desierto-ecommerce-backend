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
import java.util.stream.IntStream;

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

        totalPrice = getTotalPriceInCart();

        //count the total quantity of items in cart.
        int quantityAfterIncrement = getTotalQuantityOfItemsInCart();

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

        // Create an iterator to safely remove elements
        Iterator<ProductInCartVo> iterator = productsInCart.iterator();

        while (iterator.hasNext()) {
            ProductInCartVo tempProductInCart = iterator.next();

            if (Objects.equals(tempProductInCart.getId(), id)) {
                // If the quantity reaches zero, do not allow negative value.
                if (tempProductInCart.getQuantityInCart() <= 0) {
                    tempProductInCart.setQuantityInCart(0);
                    tempProductInCart.setSubTotalPrice(tempProductInCart.getUnitPrice()
                            .multiply(BigDecimal.valueOf(tempProductInCart.getQuantityInCart())));
                } else {
                    tempProductInCart.setQuantityInCart(tempProductInCart.getQuantityInCart() - 1);
                    tempProductInCart.setSubTotalPrice(tempProductInCart.getUnitPrice()
                            .multiply(BigDecimal.valueOf(tempProductInCart.getQuantityInCart())));
                }
            }

            if (tempProductInCart.getQuantityInCart() == 0) {
                // Safely remove the item from the collection using the iterator
                iterator.remove();
            }
        }

        BigDecimal totalPrice = getTotalPriceInCart();
        int quantityAfterDecrement = getTotalQuantityOfItemsInCart();

        cartResponse.setTotalQuantity(quantityAfterDecrement);
        cartResponse.setTotalPrice(totalPrice);
        log.info("======= DECREMENT ITEM ID : ======== " + id);
        return cartResponse;
    }


    @Override
    public CartResponse removeFromCart(Long id) {
        Optional<Product> getProductById = productRepository.findById(id);

        if (getProductById.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        int index = IntStream.range(0, productsInCart.size())
                .filter(i -> Objects.equals(productsInCart.get(i).getId(), id))
                .findFirst()
                .orElse(-1);



        log.info("========ITEM ID REMOVED FROM CART======= " + id);

         productsInCart.remove(index);

        BigDecimal totalPrice = getTotalPriceInCart();
        int totalQuantityAfterRemove = this.getTotalQuantityOfItemsInCart();

         cartResponse.setTotalQuantity(totalQuantityAfterRemove);
         cartResponse.setTotalPrice(totalPrice);
         cartResponse.setProducts(productsInCart);

         return cartResponse;

    }

    //COUNTS THE TOTAL QUANTITY OF ITEMS IN THE CART - HELPER METHOD
    private int getTotalQuantityOfItemsInCart() {
        return productsInCart.stream()
                .mapToInt(ProductInCartVo::getQuantityInCart)
                .sum();
    }

    //COUNTS THE TOTAL PRICE OF ALL ITEMS IN THE CART - HELPER METHOD
    private BigDecimal getTotalPriceInCart() {
        return productsInCart.stream()
                .map(ProductInCartVo::getSubTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

