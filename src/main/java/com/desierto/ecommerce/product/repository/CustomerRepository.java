package com.desierto.ecommerce.product.repository;

import com.desierto.ecommerce.product.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
