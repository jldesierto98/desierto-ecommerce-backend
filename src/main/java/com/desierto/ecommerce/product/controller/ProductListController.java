package com.desierto.ecommerce.product.controller;

import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.ProductResponse;
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
    public ResponseEntity<List<ProductResponse>> getProductByCategoryId(@RequestBody ProductListRequest request){
        return new ResponseEntity<>(productListService.getAllProducts(request), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProductByKeyword(@RequestBody ProductSearchRequest request){
        return new ResponseEntity<>(productListService.searchProductByKeyword(request), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> viewDetails(@PathVariable("id") Long id){
        return new ResponseEntity<>(productListService.getProductByProductId(id), HttpStatus.OK);
    }

}