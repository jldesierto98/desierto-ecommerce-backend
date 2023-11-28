package com.desierto.ecommerce.paymentprocessing.controller;

import com.desierto.ecommerce.paymentprocessing.dto.PaymentInfoDto;
import com.desierto.ecommerce.paymentprocessing.service.PaymentProcessingService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentProcessingController {

    @Autowired
    private PaymentProcessingService paymentProcessingService;

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoDto paymentInfoDto) throws StripeException{

        PaymentIntent paymentIntent = paymentProcessingService.createPaymentIntent(paymentInfoDto);
        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
}
