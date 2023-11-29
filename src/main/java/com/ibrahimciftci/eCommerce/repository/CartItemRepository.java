package com.ibrahimciftci.eCommerce.repository;

import com.ibrahimciftci.eCommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByShoppingCart_Id(Long shoppingCartId);
}