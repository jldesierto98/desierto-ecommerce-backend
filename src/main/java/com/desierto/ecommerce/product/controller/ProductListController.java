package com.desierto.ecommerce.product.controller;

import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.response.ProductListResponse;
import com.desierto.ecommerce.product.service.ProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductListController {

    private final ProductListService productListService;
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/productById")
    public ResponseEntity<List<ProductListResponse>> getProductByCategoryId(@RequestBody ProductListRequest request){
        return new ResponseEntity<>(productListService.getAllProducts(request), HttpStatus.OK);
    }



}