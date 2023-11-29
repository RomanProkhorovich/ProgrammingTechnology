package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.Role;
import com.example.ProgrammingTechnology.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DBConfig {
    private final RoleService roleService;

    @Bean
    public void createRoles(){
        roleService.createRole(new Role("Client"));
        roleService.createRole(new Role("Courier"));
        roleService.createRole(new Role("Administration"));
        roleService.createRole(new Role("Manager"));
    }
}
