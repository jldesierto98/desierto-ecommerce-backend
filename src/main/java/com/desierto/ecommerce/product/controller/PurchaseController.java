package com.desierto.ecommerce.product.controller;

import com.desierto.ecommerce.product.request.PurchaseRequest;
import com.desierto.ecommerce.product.response.PurchaseResponse;
import com.desierto.ecommerce.product.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/buy")
    public ResponseEntity<PurchaseResponse> purchaseProduct(@RequestBody PurchaseRequest purchaseRequest){
        return new ResponseEntity<>(purchaseService.purchaseProduct(purchaseRequest), HttpStatus.CREATED);
    }
}
