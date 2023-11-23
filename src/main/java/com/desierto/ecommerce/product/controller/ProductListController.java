package com.desierto.ecommerce.product.controller;

import com.desierto.ecommerce.product.request.ProductListRequest;
import com.desierto.ecommerce.product.request.ProductSearchRequest;
import com.desierto.ecommerce.product.response.CartResponse;
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

    @PostMapping("/productById")
    public ResponseEntity<List<ProductResponse>> getProductByCategoryId(@RequestBody ProductListRequest request){
        return new ResponseEntity<>(productListService.getAllProducts(request), HttpStatus.OK);
    }


    @PostMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProductByKeyword(@RequestBody ProductSearchRequest request){
        return new ResponseEntity<>(productListService.searchProductByKeyword(request), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> viewDetails(@PathVariable("id") Long id){
        return new ResponseEntity<>(productListService.getProductByProductId(id), HttpStatus.OK);
    }


    @PostMapping("/{id}")
    public ResponseEntity<CartResponse> addToCart(@PathVariable("id") Long id){
        return new ResponseEntity<>(productListService.addToCart(id), HttpStatus.CREATED);
    }


    @PostMapping("/decrement/{id}")
    public ResponseEntity<CartResponse> decrementQuantity(@PathVariable("id") Long id){
        return new ResponseEntity<>(productListService.decrementQuantity(id), HttpStatus.OK);
    }


    @PostMapping("/remove/{id}")
    public ResponseEntity<CartResponse> removeItemFromCart(@PathVariable("id") Long id){
        return new ResponseEntity<>(productListService.removeFromCart(id), HttpStatus.CREATED);
    }


    //will modift this soon to get cart items of a certain user.
    @GetMapping("/cart")
    public ResponseEntity<CartResponse> getCartItems(){
        return new ResponseEntity<>(productListService.getCartItems(), HttpStatus.OK);
    }



}