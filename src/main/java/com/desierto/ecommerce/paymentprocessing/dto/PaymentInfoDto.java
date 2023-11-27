package com.desierto.ecommerce.paymentprocessing.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentInfoDto {

    private int amount;
    private String currency;
}
