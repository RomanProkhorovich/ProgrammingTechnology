package com.example.ProgrammingTechnology.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class DishDto {
    private Long id;
    private String name;
    private Float price;
    private Float calories;
    private Float weight;

    public DishDto(String name, Float price, Float calories, Float weight) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishDto dishDto = (DishDto) o;
        return Objects.equals(name, dishDto.name) && Objects.equals(price, dishDto.price) && Objects.equals(calories, dishDto.calories) && Objects.equals(weight, dishDto.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, calories, weight);
    }
}
