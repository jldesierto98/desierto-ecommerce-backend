package com.desierto.ecommerce.product.response;


import com.desierto.ecommerce.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AddToCartResponse {

    private BigDecimal totalPrice;
    private Integer quantity;
    private List<Product> products;
}
