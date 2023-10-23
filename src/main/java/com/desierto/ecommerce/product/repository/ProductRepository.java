package com.desierto.ecommerce.product.repository;

import com.desierto.ecommerce.product.entity.Product;
import com.desierto.ecommerce.product.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //find by category ID
    @Query(nativeQuery = true)
    List<ProductResponse> findProductByCategory_Named(@Param("id") Long id, Pageable pageable);

    //find by product ID
    @Query(nativeQuery = true)
    ProductResponse findByProductId_Named(@Param("id") Long id);

    @Query(nativeQuery = true)
    List<ProductResponse> findProductByKeyword_Named(@Param("keyword") String keyword, Pageable pageable);

    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

    Optional<Product> findById(@Param("id") Long id);

}
