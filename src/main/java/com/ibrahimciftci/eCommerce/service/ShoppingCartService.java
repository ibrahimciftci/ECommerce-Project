package com.ibrahimciftci.eCommerce.service;

import com.ibrahimciftci.eCommerce.dto.CartItemDTO;
import com.ibrahimciftci.eCommerce.entity.CartItem;
import com.ibrahimciftci.eCommerce.entity.Product;
import com.ibrahimciftci.eCommerce.entity.ShoppingCart;
import com.ibrahimciftci.eCommerce.entity.User;
import com.ibrahimciftci.eCommerce.repository.CartItemRepository;
import com.ibrahimciftci.eCommerce.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    public ShoppingCart createShoppingCart(Long userId) {
        User user = new User();
        user.setId(userId);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }

    public List<CartItem> getCartItems(Long shoppingCartId) {
        return cartItemRepository.findByShoppingCart_Id(shoppingCartId);
    }

    public void addProductToCart(Long shoppingCartId, CartItemDTO cartItemDTO) {
        Product product = productService.getProductById(cartItemDTO.getProductId());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartId);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        cartItemRepository.save(cartItem);
    }

    public ShoppingCart getShoppingCartByUserId(Long userId) {
        return null;
    }

    public void clearShoppingCart(Long id) {

    }

    // Diğer sepet işlemleri metotları...
}