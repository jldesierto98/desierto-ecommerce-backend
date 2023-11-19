package com.desierto.ecommerce.product.repository;

import com.desierto.ecommerce.product.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    @CrossOrigin("http://localhost:4200")
    List<State> findByCountryId(Long id);
}
