package com.desierto.ecommerce.product.repository;

import com.desierto.ecommerce.product.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByCode(String code);
}
