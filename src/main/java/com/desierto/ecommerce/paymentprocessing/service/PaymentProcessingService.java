package com.desierto.ecommerce.paymentprocessing.service;

import com.desierto.ecommerce.paymentprocessing.dto.PaymentInfoDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentProcessingService {

    PaymentIntent createPaymentIntent(PaymentInfoDto paymentInfoDto) throws StripeException;

}
