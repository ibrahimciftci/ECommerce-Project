package com.ibrahimciftci.eCommerce.repository;

import com.ibrahimciftci.eCommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByNameContainingIgnoreCase(String keyword);
    // Ek sorgular
}