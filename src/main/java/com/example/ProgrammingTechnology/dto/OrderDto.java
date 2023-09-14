package com.example.ProgrammingTechnology.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Set<DishDto> dishDtos = new LinkedHashSet<>();
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
