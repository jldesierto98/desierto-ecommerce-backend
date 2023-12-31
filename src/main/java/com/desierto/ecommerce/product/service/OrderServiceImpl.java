package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.entity.Order;
import com.desierto.ecommerce.product.repository.OrdersRepository;
import com.desierto.ecommerce.product.request.GetOrderHistoryRequest;
import com.desierto.ecommerce.product.response.OrderHistoryResponse;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrdersRepository ordersRepository;

    @Override
    public List<OrderHistoryResponse> getOrderByCustomerEmail(GetOrderHistoryRequest request) {
        Pageable pageable = PageRequest.of(0, 20);

        log.info("Executing query findByCustomerEmail for email: {}", request.getEmail());

        List<Order> queriedUserOrders = ordersRepository.findByCustomerEmail(request.getEmail(), pageable);
        List<OrderHistoryResponse> orderHistoryResponse = new ArrayList<>();

        queriedUserOrders.forEach(queriedOrder -> {
            orderHistoryResponse.add(OrderHistoryResponse.builder()
                    .orderTrackingNumber(queriedOrder.getOrderTrackingNumber())
                    .totalPrice(queriedOrder.getTotalPrice())
                    .totalQuantity(queriedOrder.getTotalQuantity())
                    .date(queriedOrder.getDateCreated()).build());
        });

        log.info("=======_____ Order History _____======= : {}", orderHistoryResponse.toString());

        return orderHistoryResponse;
    }

    @Override
    public List<Order> getOrdersByCustId(Long id) {
        return ordersRepository.findByCustomerId(id);
    }
}
