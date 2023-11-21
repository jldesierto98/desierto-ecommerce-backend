package com.desierto.ecommerce.product.service;

import com.desierto.ecommerce.product.entity.Customer;
import com.desierto.ecommerce.product.entity.OrderItem;
import com.desierto.ecommerce.product.entity.Order;
import com.desierto.ecommerce.product.repository.CustomerRepository;
import com.desierto.ecommerce.product.request.PurchaseRequest;
import com.desierto.ecommerce.product.response.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseServiceImpl implements PurchaseService{

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public PurchaseResponse purchaseProduct(PurchaseRequest purchaseRequest) {


        Order order = purchaseRequest.getOrders();

        String orderTrackingNumber = UUID.randomUUID().toString();
        order.setOrderTrackingNumber(orderTrackingNumber);
        order.setShippingAddress(purchaseRequest.getShippingAddress());
        order.setBillingAddress(purchaseRequest.getBillingAddress());

        Set<OrderItem> orderItemSet = purchaseRequest.getOrderItems();
        orderItemSet.forEach(order::add);

        Customer customer = purchaseRequest.getCustomer();

        customer.add(order);

        customerRepository.save(customer);

        log.info("========Order Items ======== : " + orderItemSet);
        log.info("=======Customer======== : " + customer);
        log.info("============Order========= : " + order);
        return PurchaseResponse.builder()
                .orderTrackingNumber(orderTrackingNumber)
                .build();
    }
}
