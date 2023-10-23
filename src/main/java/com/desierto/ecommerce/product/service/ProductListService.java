package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.CartResponse;
import com.desierto.ecommerce.product.response.ProductResponse;

import java.util.List;

/**
 * @Author Joshua L. Desierto
 */
public interface ProductListService {

   List<ProductResponse> getAllProducts(ProductListRequest request);

   List<ProductResponse> searchProductByKeyword(ProductSearchRequest request);

   ProductResponse getProductByProductId(Long id);

   /**
    *
    * @param id
    * @return CartResponse
    * This API handles the add to cart function for an item (based on id) which calculates
    * total quantity, total price, and the product details inside the cart.
    *
    * This API is also used to increment number of items.
    */
   CartResponse addToCart(Long id);

   /**
    *
    * @param id
    * @return CartResponse
    *
    * This API handles the decrement quantity of an item.
    */
   CartResponse decrementQuantity(Long id);

   /**
    *
    * @param id
    * @return List<CartResponse>
    * This method removes an item from a cart using item id.
    */
   CartResponse removeFromCart(Long id);

   /**
    *
    * @return CartResponse
    * Will modify soon to get cart of a specific user.
    */
   CartResponse getCartItems();


}
