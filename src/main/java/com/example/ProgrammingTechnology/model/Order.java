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
    @JoinTable(name = "Order_Dish",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "dishes_id"))
    private Set<Dish> dishes = new LinkedHashSet<>();

    @Column(nullable = false, name = "order_time")
    private LocalDateTime orderTime;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;


    @Column(nullable = false, name = "delivery_time")
    private LocalDateTime deliveryTime;

    @OneToOne
    @JoinColumn(name = "courier_id")
    private User courier;

    @Column(name = "is_offline", nullable = false)
    private Boolean isOffline;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private User user;

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
