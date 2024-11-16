package org.bits.orderservice.model;

import lombok.*;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusUpdateRequest {
    private String status;
}

