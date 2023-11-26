package com.example.ProgrammingTechnology.mapper;

import com.example.ProgrammingTechnology.dto.UserDto;
import com.example.ProgrammingTechnology.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    User toModel(UserDto dto);

    UserDto toDto(User model);

    List<User> toModelList(List<UserDto> dto);

    List<UserDto> toDtoList(List<User> model);
}
