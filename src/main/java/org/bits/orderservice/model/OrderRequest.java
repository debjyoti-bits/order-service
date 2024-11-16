package org.bits.orderservice.model;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long customerId;
    private Long restaurantId;
    private String status;
    private BigDecimal totalAmount;
    private List<OrderItem> orderItems;

    // Getters and Setters
}
