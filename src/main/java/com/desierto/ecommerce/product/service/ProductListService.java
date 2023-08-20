package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.AddToCartResponse;
import com.desierto.ecommerce.product.response.ProductResponse;

import java.util.List;

public interface ProductListService {

   List<ProductResponse> getAllProducts(ProductListRequest request);

   List<ProductResponse> searchProductByKeyword(ProductSearchRequest request);

   ProductResponse getProductByProductId(Long id);

   AddToCartResponse addToCart(Long id);
}
