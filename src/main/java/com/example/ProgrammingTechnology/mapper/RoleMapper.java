package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.RoleDto;
import com.example.ProgrammingTechnology.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toModel(RoleDto dto);

    RoleDto toDto(Role dish);

    List<Role> toModelList(List<RoleDto> dto);

    List<RoleDto> toDtoList(List<Role> dish);
}
