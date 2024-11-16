package org.bits.orderservice.model;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private String status;
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;
}


