package com.example.ProgrammingTechnology.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private DishDto dishDto;
    private OrderDto orderDto;
    private Byte count;

    public CartItemDto(DishDto dishDto, OrderDto orderDto, Byte count) {
        this.dishDto = dishDto;
        this.orderDto = orderDto;
        this.count=count;
    }
}
