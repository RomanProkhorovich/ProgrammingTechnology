package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.ReceivingTypeDto;
import com.example.ProgrammingTechnology.model.ReceivingType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceivingTypeMapper {

    ReceivingType toModel(ReceivingTypeDto dto);

    ReceivingTypeDto toDto(ReceivingType model);

    List<ReceivingType> toModelList(List<ReceivingTypeDto> dto);

    List<ReceivingTypeDto> toDtoList(List<ReceivingType> model);
}
