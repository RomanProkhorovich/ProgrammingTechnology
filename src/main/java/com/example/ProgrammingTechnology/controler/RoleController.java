package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.RoleDto;
import com.example.ProgrammingTechnology.mapper.RoleMapper;
import com.example.ProgrammingTechnology.service.RoleService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleMapper mapper;
    private final RoleService service;

    @GetMapping
    public List<RoleDto> findAll() {
        return mapper.toDtoList(service.findRoles());
    }
/*

    @GetMapping
    public RoleDto findByName(@PathParam("name") String name) {
        return mapper.toDto(service.findRoleByName(name));
    }

    @GetMapping
    public RoleDto findById(@PathParam("id") Long id) {
        return mapper.toDto(service.findRoleById(id));
    }
*/

}
