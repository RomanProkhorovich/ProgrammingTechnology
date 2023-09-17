package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.OrderDishDto;
import com.example.ProgrammingTechnology.dto.OrderDto;
import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.OrderDish;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DishMapper.class, OrderMapper.class})
public interface OrderDishMapper {

    OrderDish toModel(OrderDishDto dto);
    OrderDishDto toDto(OrderDish model);

    List<OrderDish> toModelList(List<OrderDishDto> dto);
    List<OrderDishDto> toDtoList(List<OrderDish> model);

}
