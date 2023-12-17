package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.ReceivingType;
import com.example.ProgrammingTechnology.model.Role;
import com.example.ProgrammingTechnology.service.ReceivingTypeService;
import com.example.ProgrammingTechnology.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DBConfig {
    private final RoleService roleService;
    private final ReceivingTypeService receivingTypeService;

    @Bean
    public void createRoles(){
        roleService.createRole(new Role("Client"));
        roleService.createRole(new Role("Courier"));
        roleService.createRole(new Role("Administration"));
        roleService.createRole(new Role("Manager"));
    }


    @Bean
    public void createRecType(){
        receivingTypeService.createReceivingType(new ReceivingType("Самовывоз"));
        receivingTypeService.createReceivingType(new ReceivingType("Курьер на дом"));
        receivingTypeService.createReceivingType(new ReceivingType("В ресторане"));
    }
}
