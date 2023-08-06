package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.ProductListResponse;

import java.util.List;

public interface ProductListService {

   List<ProductListResponse> getAllProducts(ProductListRequest request);

   List<ProductListResponse> searchProductByKeyword(ProductSearchRequest request);
}
