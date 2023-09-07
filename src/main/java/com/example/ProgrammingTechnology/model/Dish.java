package com.example.ProgrammingTechnology.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dish {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Float calories;


    @Column(nullable = false)
    private Float weight;

    public Dish(String name, Float price, Float calories, Float weight) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(name, dish.name) && Objects.equals(price, dish.price) && Objects.equals(calories, dish.calories) && Objects.equals(weight, dish.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, calories, weight);
    }
}
