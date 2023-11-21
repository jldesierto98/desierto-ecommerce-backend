package com.desierto.ecommerce.product.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrderHistoryResponse {

    private String orderTrackingNumber;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private Date date;

}
