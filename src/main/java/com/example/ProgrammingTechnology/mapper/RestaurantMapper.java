package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.RestaurantDto;
import com.example.ProgrammingTechnology.model.Restaurant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {KitchenTypeMapper.class, UserMapper.class, MenuMapper.class})
public interface RestaurantMapper {

    Restaurant toModel(RestaurantDto dto);

    RestaurantDto toDto(Restaurant model);

    List<Restaurant> toModelList(List<RestaurantDto> dto);

    List<RestaurantDto> toDtoList(List<Restaurant> model);
}
