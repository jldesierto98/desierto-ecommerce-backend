package com.desierto.ecommerce.product.request;


import com.desierto.ecommerce.product.entity.Address;
import com.desierto.ecommerce.product.entity.Customer;
import com.desierto.ecommerce.product.entity.OrderItem;
import com.desierto.ecommerce.product.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PurchaseRequest {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order orders;

    private Set<OrderItem> orderItems;


}
