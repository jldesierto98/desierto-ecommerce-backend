package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.entity.Order;
import com.desierto.ecommerce.product.request.GetOrderHistoryRequest;
import com.desierto.ecommerce.product.response.OrderHistoryResponse;

import java.util.List;

public interface OrderService {

    List<OrderHistoryResponse> getOrderByCustomerEmail(GetOrderHistoryRequest request);

    List<Order> getOrdersByCustId(Long id);
}
