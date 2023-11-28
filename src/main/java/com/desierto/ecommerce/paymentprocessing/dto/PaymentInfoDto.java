package com.desierto.ecommerce.paymentprocessing.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentInfoDto {

    //Stripe handling of amount is int, lowest denomination of cent.
    private int amount;
    private String currency;

}
