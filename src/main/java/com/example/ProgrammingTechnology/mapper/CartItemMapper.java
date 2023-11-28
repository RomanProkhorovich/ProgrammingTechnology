package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.CartItemDto;
import com.example.ProgrammingTechnology.model.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DishMapper.class, OrderMapper.class})
public interface CartItemMapper {

    CartItem toModel(CartItemDto dto);

    CartItemDto toDto(CartItem model);

    List<CartItem> toModelList(List<CartItemDto> dto);

    List<CartItemDto> toDtoList(List<CartItem> model);

}
