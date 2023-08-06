package com.desierto.ecommerce.product.response;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductListResponse {
    private Long id;
    private String imageUrl;
    private String name;
    private BigDecimal unitPrice;

}
