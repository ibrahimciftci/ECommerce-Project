package com.ibrahimciftci.eCommerce.service;

import com.ibrahimciftci.eCommerce.model.CartItem;
import com.ibrahimciftci.eCommerce.model.Order;
import com.ibrahimciftci.eCommerce.model.OrderItem;
import com.ibrahimciftci.eCommerce.model.ShoppingCart;
import com.ibrahimciftci.eCommerce.repository.OrderItemRepository;
import com.ibrahimciftci.eCommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ShoppingCartService shoppingCartService;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.shoppingCartService = shoppingCartService;
    }

    public Order createOrder(Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUserId(userId);

        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setTotalAmount(calculateTotalAmount(shoppingCart.getCartItems()));
        order.setOrderDate(LocalDateTime.now());

        order = orderRepository.save(order);

        // Sepetteki ürünleri siparişe ekleme
        List<CartItem> cartItems = shoppingCart.getCartItems();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getProduct().getPrice());

            orderItemRepository.save(orderItem);
        }

        // Sepeti temizleme
        shoppingCartService.clearShoppingCart(shoppingCart.getId());

        return order;
    }

    private BigDecimal calculateTotalAmount(List<CartItem> cartItems) {
        // Sepetteki ürünlerin toplam tutarını hesaplama
        return null;
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }

    // Diğer sipariş işlemleri metotları...
}