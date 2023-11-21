package com.desierto.ecommerce.product.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderHistoryResponse {

    private String orderTrackingNumber;
    private BigDecimal totalPrice;
    private Integer totalQuantity;


}
