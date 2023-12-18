package com.example.ProgrammingTechnology.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_order")
public class Order {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "order_time")
    @Builder.Default
    private LocalDateTime orderTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;


    @Column(nullable = false, name = "delivery_time")
    private LocalDateTime deliveryTime;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private User courier;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "receiving_type")
    private ReceivingType receivingType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany
    @JoinColumn(name = "order_id")
    private Set<CartItem> cartItems = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
