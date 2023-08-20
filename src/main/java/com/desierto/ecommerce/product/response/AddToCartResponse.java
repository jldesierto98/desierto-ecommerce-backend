package com.desierto.ecommerce.product.response;

import com.desierto.ecommerce.vo.ProductInCartVo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AddToCartResponse {

    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private List<ProductInCartVo> products;
}
