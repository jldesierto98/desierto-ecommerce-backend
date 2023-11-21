package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.entity.Order;
import com.desierto.ecommerce.product.request.GetOrderHistoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    List<Order> getOrderByCustomerEmail(GetOrderHistoryRequest request);

    List<Order> getOrdersByCustId(Long id);
}
