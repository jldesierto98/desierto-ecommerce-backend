package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.repository.ProductRepository;
import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.ProductListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductListServiceImpl implements ProductListService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductListResponse> getAllProducts(ProductListRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        return productRepository.findProductById_Named(request.getId(), pageable);
    }

    @Override
    public List<ProductListResponse> searchProductByKeyword(ProductSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        return productRepository.findProductByKeyword_Named(request.getKeyword());
    }
}
