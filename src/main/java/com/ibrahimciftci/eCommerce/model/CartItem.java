package com.ibrahimciftci.eCommerce.model;

import com.ibrahimciftci.eCommerce.dto.CartItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    private int quantity;



    public static CartItem fromDto(CartItemDTO cartItemDTO){
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        cartItem.setProduct(cartItemDTO.getProduct());
        cartItem.setShoppingCart(cartItemDTO.getShoppingCart());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        return cartItem;
    }
}