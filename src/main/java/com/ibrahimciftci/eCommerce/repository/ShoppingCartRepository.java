package com.ibrahimciftci.eCommerce.repository;

import com.ibrahimciftci.eCommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUser_Id(Long userId);
}