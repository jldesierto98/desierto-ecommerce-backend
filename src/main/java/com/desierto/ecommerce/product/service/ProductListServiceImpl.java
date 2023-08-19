package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.repository.ProductRepository;
import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.ProductResponse;
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
}
