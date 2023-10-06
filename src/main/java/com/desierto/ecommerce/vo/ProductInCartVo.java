package com.desierto.ecommerce.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductInCartVo {
    private Long id;
    private String name;
    private String imageUrl;
    private BigDecimal unitPrice;
    private BigDecimal subTotalPrice;
    private Integer quantityInCart;

}
