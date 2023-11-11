package com.desierto.ecommerce.product.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PurchaseResponse {
    private String orderTrackingNumber;
}
