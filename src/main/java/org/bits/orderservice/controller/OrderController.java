package org.bits.orderservice.controller;

import org.bits.orderservice.model.Order;
import org.bits.orderservice.model.OrderRequest;
import org.bits.orderservice.model.OrderStatusUpdateRequest;
import org.bits.orderservice.model.User;
import org.bits.orderservice.service.OrderService;
import org.bits.orderservice.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(
                orderRequest.getCustomerId(),
                orderRequest.getRestaurantId(),
                orderRequest.getStatus(),
                orderRequest.getTotalAmount(),
                orderRequest.getOrderItems()
        );
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusUpdateRequest orderStatusUpdateRequest) {
        Order updatedOrder = orderService.updateOrderStatus(id, orderStatusUpdateRequest.getStatus());
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/history")
    public ResponseEntity<List<Order>> getOrderHistory() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User useDetails = userDetailsService.getUserIdFromName(authentication.getName());
        Long customerId = useDetails.getId();
        List<Order> orderHistory = orderService.getOrderHistory(customerId);
        return ResponseEntity.ok(orderHistory);
    }
}
