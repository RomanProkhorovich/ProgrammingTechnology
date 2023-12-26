package com.example.ProgrammingTechnology.dto;

import com.example.ProgrammingTechnology.model.CartItem;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    @Builder.Default
    private Set<CartItemDto> cartItems = new LinkedHashSet<>();
    @Builder.Default
    private LocalDateTime orderTime = LocalDateTime.now();
    private OrderStatusDto orderStatus;
    private LocalDateTime deliveryTime;
    private UserDto courier;
    private String address;
    private RestaurantDto restaurant;
    private ReceivingTypeDto receivingType;
    private UserDto client;

    private PayMethodDto payMethod;

    public Float getSum(){
        return cartItems
                .stream()
                .map(x->x.getCount()*x.getDish().getPrice())
                .reduce(0f, Float::sum);
    }

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
