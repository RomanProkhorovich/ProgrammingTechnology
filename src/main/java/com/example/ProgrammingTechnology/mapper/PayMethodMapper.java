package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.PayMethodDto;
import com.example.ProgrammingTechnology.dto.RoleDto;
import com.example.ProgrammingTechnology.model.PayMethod;
import com.example.ProgrammingTechnology.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PayMethodMapper {

    PayMethod toModel(PayMethodDto dto);

    PayMethodDto toDto(PayMethod dish);

    List<PayMethod> toModelList(List<PayMethodDto> dto);

    List<PayMethodDto> toDtoList(List<PayMethod> dish);

}
