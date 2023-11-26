package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.KitchenTypeDto;
import com.example.ProgrammingTechnology.model.KitchenType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KitchenTypeMapper {
    KitchenType toModel(KitchenTypeDto dto);

    KitchenTypeDto toDto(KitchenType dish);

    List<KitchenType> toModelList(List<KitchenTypeDto> dto);

    List<KitchenTypeDto> toDtoList(List<KitchenType> dish);
}



