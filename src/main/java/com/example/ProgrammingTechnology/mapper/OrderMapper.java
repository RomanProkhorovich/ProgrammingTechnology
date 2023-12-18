package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.CreateOrderDto;
import com.example.ProgrammingTechnology.dto.OrderDto;
import com.example.ProgrammingTechnology.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        DishMapper.class,
        UserMapper.class,
        RestaurantMapper.class,
        ReceivingTypeMapper.class,
        OrderStatusMapper.class
})
public interface OrderMapper {

    Order toModel(OrderDto dto);

    OrderDto toDto(Order model);

    List<Order> toModelList(List<OrderDto> dto);

    List<OrderDto> toDtoList(List<Order> model);


}
