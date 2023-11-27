package com.desierto.ecommerce.paymentprocessing.service;

import com.desierto.ecommerce.paymentprocessing.dto.PaymentInfoDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PaymentProcessingServiceImpl implements PaymentProcessingService{

    public PaymentProcessingServiceImpl(@Value("${stripe.key.secret}") String secretKey) {
        Stripe.apiKey = secretKey;
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfoDto paymentInfoDto) throws StripeException {

        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfoDto.getAmount());
        params.put("currency",paymentInfoDto.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);


        return PaymentIntent.create(params);
    }
}
