package com.ibrahimciftci.eCommerce.dto;

import com.ibrahimciftci.eCommerce.model.CartItem;
import com.ibrahimciftci.eCommerce.model.Product;
import com.ibrahimciftci.eCommerce.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {
    private Long id;
    private Product product;
    private ShoppingCart shoppingCart;

    private int quantity;


    public static CartItemDTO fromEntity(CartItem cartItem){
        return new CartItemDTO(cartItem.getId(), cartItem.getProduct(), cartItem.getShoppingCart(), cartItem.getQuantity());
    }

    public Long getProductId() {
        return null;
    }
}