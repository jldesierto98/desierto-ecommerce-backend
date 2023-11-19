package com.desierto.ecommerce.product.repository;

import com.desierto.ecommerce.product.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByCode(String code);
}
