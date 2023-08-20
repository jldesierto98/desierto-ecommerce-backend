package com.desierto.ecommerce.vo;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInCartVo {
    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal subTotalPrice;
    private Integer quantityInCart;

}
