package com.example.ProgrammingTechnology.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "app_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "order_time")
    @Builder.Default
    private LocalDateTime orderTime = LocalDateTime.now();

    //TODO: сделать обязательным, сделать значение по дефолту???
    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;


    @Column(name = "delivery_time")
    private LocalDateTime deliveryTime;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private User courier;

    @Column(nullable = false)
    private String address;

    //TODO: Рома проверь
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "receiving_type", nullable = false)
    private ReceivingType receivingType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    //TODO: хз
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
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
