package com.example.ProgrammingTechnology.controler;


import com.example.ProgrammingTechnology.dto.RoleDto;
import com.example.ProgrammingTechnology.dto.UserDto;
import com.example.ProgrammingTechnology.mapper.RoleMapper;
import com.example.ProgrammingTechnology.mapper.UserMapper;
import com.example.ProgrammingTechnology.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public UserDto findById(@PathParam("id") Long id){
        return mapper.toDto(service.findUserById(id));
    }


    @GetMapping
    public UserDto findById(@PathParam("email") String email){
        return mapper.toDto(service.findUserByEmail(email));
    }

    @GetMapping
    public List<UserDto> findAllByRole(@PathParam("role_id") Long roleId){
        return mapper.toDtoList(service.findUsersByRole(roleId));
    }

    @GetMapping
    public List<UserDto> findAll(){
        return mapper.toDtoList(service.findUsers());
    }
}
