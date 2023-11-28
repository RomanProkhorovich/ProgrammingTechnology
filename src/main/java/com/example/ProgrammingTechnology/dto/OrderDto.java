package com.example.ProgrammingTechnology.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private List<DishDto> dishDtos;
    @Builder.Default
    private LocalDateTime orderTime = LocalDateTime.now();
    private OrderStatusDto orderStatusDto;
    private LocalDateTime deliveryTime;
    private UserDto courier;
    private String address;
    private RestaurantDto restaurantDto;
    private ReceivingTypeDto receivingTypeDto;
    private UserDto client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
