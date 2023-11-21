package com.desierto.ecommerce.product.controller;

import com.desierto.ecommerce.product.entity.Order;
import com.desierto.ecommerce.product.request.GetOrderHistoryRequest;
import com.desierto.ecommerce.product.response.OrderHistoryResponse;
import com.desierto.ecommerce.product.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @GetMapping("/orderHistory")
    public ResponseEntity<List<OrderHistoryResponse>> getOrderHistory(@RequestBody GetOrderHistoryRequest request){
        return new ResponseEntity<>(orderService.getOrderByCustomerEmail(request), HttpStatus.OK);
    }

    @PostMapping("/orderHistory/{id}")
    public ResponseEntity<List<Order>> getOrderHistroyByCustId(@PathVariable("id") Long id){
        return new ResponseEntity<>(orderService.getOrdersByCustId(id), HttpStatus.OK);
    }


}
