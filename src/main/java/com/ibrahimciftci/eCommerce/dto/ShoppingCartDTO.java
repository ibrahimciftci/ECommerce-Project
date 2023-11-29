package com.ibrahimciftci.eCommerce.dto;

import com.ibrahimciftci.eCommerce.model.CartItem;
import com.ibrahimciftci.eCommerce.model.ShoppingCart;
import com.ibrahimciftci.eCommerce.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartDTO {
    private Long id;
    private User user;
    private List<CartItem> cartItems = new ArrayList<>();


    public static ShoppingCartDTO fromEntity(ShoppingCart shoppingCart){
        return new ShoppingCartDTO(shoppingCart.getId(), shoppingCart.getUser(), shoppingCart.getCartItems());
    }
}