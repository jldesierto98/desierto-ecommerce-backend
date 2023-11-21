package com.desierto.ecommerce.product.repository;

import com.desierto.ecommerce.product.entity.Order;
import com.desierto.ecommerce.product.response.OrderHistoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

//    @Query(nativeQuery = true)
//    List<OrderHistoryResponse> findOrderByCustomerEmail_Named(@Param("email") String email, Pageable pageable);

//    @Query("SELECT o FROM orders o WHERE o.customer.email = :email")
//    List<Order> findAllOrdersByCustomerEmail(@Param("email") String email);

    List<Order> findByCustomerEmail(String customerEmail, Pageable pageable);


    List<Order> findByCustomerId(Long id);
}
