package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.OrderStatusDto;
import com.example.ProgrammingTechnology.model.OrderStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    OrderStatus toModel(OrderStatusDto dto);

    OrderStatusDto toDto(OrderStatus model);

    List<OrderStatus> toModelList(List<OrderStatusDto> dto);

    List<OrderStatusDto> toDtoList(List<OrderStatus> model);
}
