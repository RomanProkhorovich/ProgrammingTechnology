package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.KitchenTypeDto;
import com.example.ProgrammingTechnology.dto.MenuDto;
import com.example.ProgrammingTechnology.model.KitchenType;
import com.example.ProgrammingTechnology.model.Menu;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DishMapper.class})
public interface MenuMapper {

    Menu toModel(KitchenTypeDto dto);
    MenuDto toDto(Menu dish);

    List<Menu> toModelList(List<MenuDto> dto);
    List<MenuDto> toDtoList(List<Menu> dish);
}
