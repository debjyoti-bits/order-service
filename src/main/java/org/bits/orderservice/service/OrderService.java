package org.bits.orderservice.service;

import org.bits.orderservice.model.Order;
import org.bits.orderservice.model.OrderItem;
import org.bits.orderservice.model.Restaurant;
import org.bits.orderservice.repositories.OrderRepository;
import org.bits.orderservice.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }


    public Order createOrder(Long customerId, Long restaurantId, String status, BigDecimal totalAmount, List<OrderItem> orderItems) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setRestaurant(restaurant);  // Set the restaurant property
        order.setStatus(status);
        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);

        // Set order for each order item
        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrderStatus(Long id, String status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

    public List<Order> getOrderHistory(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}