package org.bits.orderservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;
    private String name;
    private String address;
    private String hoursOfOperation;
    private String deliveryZones;
}
