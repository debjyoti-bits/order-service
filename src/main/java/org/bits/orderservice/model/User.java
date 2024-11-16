package org.bits.orderservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;
}
