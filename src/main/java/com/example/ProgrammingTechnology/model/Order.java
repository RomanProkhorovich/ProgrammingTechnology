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
public class Order {
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @Builder.Default
    @JoinTable(name = "Order_dishes",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dishes_id"))
    private Set<Dish> dishes = new LinkedHashSet<>();

    @Column(nullable = false)
    private LocalDateTime orderTime;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;


    @Column(nullable = false)
    private LocalDateTime deliveryTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User courier;

    public Order(Set<Dish> dishes, LocalDateTime orderTime, OrderStatus orderStatus, LocalDateTime deliveryTime, User courier) {
        this.dishes = dishes;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.deliveryTime = deliveryTime;
        this.courier = courier;
    }

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
