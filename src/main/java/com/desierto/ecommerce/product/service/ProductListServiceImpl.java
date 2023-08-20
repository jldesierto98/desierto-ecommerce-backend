package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.exception.ProductNotFoundException;
import com.desierto.ecommerce.product.entity.Product;
import com.desierto.ecommerce.product.repository.ProductRepository;
import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.AddToCartResponse;
import com.desierto.ecommerce.product.response.ProductResponse;
import com.desierto.ecommerce.vo.ProductInCartVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductListServiceImpl implements ProductListService {

    private final ProductRepository productRepository;
    private List<ProductInCartVo> productsInCart = new ArrayList<>(); //temporary storage
    private int addToCartCounter = 0;
    private BigDecimal totalPrice;


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

        //If product is not found, throw this custom error.
        if (getProductById.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        totalPrice = BigDecimal.ZERO;
        final int ADD_ONE_TO_CART = 1;
        AddToCartResponse addToCartResponse = new AddToCartResponse();
        ProductInCartVo productInCart = new ProductInCartVo();
        boolean productAlreadyInCart = false;
        addToCartCounter++;

        Product product = getProductById.get();


        //build productInCart
        productInCart.setId(product.getId());
        productInCart.setName(product.getName());
        productInCart.setUnitPrice(product.getUnitPrice());
        productInCart.setQuantityInCart(ADD_ONE_TO_CART);
        productInCart.setSubTotalPrice(product.getUnitPrice());

        //build subtotal price and quantityInCart
        //check if the product added in cart is already added in cart before.
        for(ProductInCartVo tempProductInCart : productsInCart){

            if(tempProductInCart.getId().equals(product.getId())){
                productAlreadyInCart = true;
                break;
            }

        }


        //if yes, then increment quantity in cart and compute subTotal.
        if(productAlreadyInCart){

            for(ProductInCartVo productInCartExisting : productsInCart){

                if(productInCartExisting.getId().equals(product.getId())){

                    productInCartExisting.setQuantityInCart(productInCartExisting.getQuantityInCart() + 1);

                    productInCartExisting.setSubTotalPrice(productInCartExisting.getUnitPrice()
                            .multiply(BigDecimal.valueOf(productInCartExisting.getQuantityInCart())));
                }

            }

        }else{
            productsInCart.add(productInCart);
        }

        for(ProductInCartVo productSubTotal : productsInCart){
            totalPrice = totalPrice.add(productSubTotal.getSubTotalPrice());
        }


        addToCartResponse.setProducts(productsInCart);
        addToCartResponse.setTotalPrice(totalPrice);
        addToCartResponse.setTotalQuantity(addToCartCounter);

        return addToCartResponse;
    }

}
