package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.CartResponse;
import com.desierto.ecommerce.product.response.ProductResponse;

import java.util.List;

public interface ProductListService {

   List<ProductResponse> getAllProducts(ProductListRequest request);

   List<ProductResponse> searchProductByKeyword(ProductSearchRequest request);

   ProductResponse getProductByProductId(Long id);

   /**
    *
    * @param id
    * @return AddToCartResponse
    * This API handles the add to cart function for an item (based on id) which calculates
    * total quantity, total price, and the product details inside the cart.
    *
    * This API is also used to increment number of items.
    */
   CartResponse addToCart(Long id);

   /**
    *
    * @param id
    * @return AddToCartResponse
    *
    * This API handles the decrement quantity of an item.
    */
   CartResponse decrementQuantity(Long id);
}
