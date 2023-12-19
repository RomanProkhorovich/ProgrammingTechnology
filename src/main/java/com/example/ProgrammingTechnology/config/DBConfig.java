package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.ReceivingType;
import com.example.ProgrammingTechnology.model.Role;
import com.example.ProgrammingTechnology.service.KitchenTypeService;
import com.example.ProgrammingTechnology.service.OrderStatusService;
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
    private final KitchenTypeService kitchenTypeService;

    @Bean
    public int createRoles(){
        roleService.createRole(new Role("Client"));
        roleService.createRole(new Role("Courier"));
        roleService.createRole(new Role("Administration"));
        roleService.createRole(new Role("Manager"));
        return 1;
    }


    @Bean
    public int createRecType(){
        receivingTypeService.createReceivingType(new ReceivingType("Самовывоз"));
        receivingTypeService.createReceivingType(new ReceivingType("Курьер"));
        receivingTypeService.createReceivingType(new ReceivingType("В ресторане"));
        return 1;
    }
}
