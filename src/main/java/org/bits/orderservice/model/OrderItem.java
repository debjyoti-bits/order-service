package org.bits.orderservice.model;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    private Integer quantity;
    private BigDecimal price;

    // Getters and Setters
}
