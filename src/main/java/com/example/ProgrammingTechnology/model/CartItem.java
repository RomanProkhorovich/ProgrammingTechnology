package com.example.ProgrammingTechnology.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Order_dishes")
public class CartItem {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dish_id")
    private Dish dish;
/*

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
*/

    @Column(nullable = false)
    private Byte count;

    public CartItem(Dish dish, Byte count) {
        this.dish = dish;
        this.count=count;
    }
}
