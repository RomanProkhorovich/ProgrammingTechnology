package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.DishDto;
import com.example.ProgrammingTechnology.model.Dish;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishMapper {
    Dish toModel(DishDto dto);
    DishDto toDto(Dish dish);

    List<Dish> toModelList(List<DishDto> dto);
    List<DishDto> toDtoList(List<Dish> dish);

}
