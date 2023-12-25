package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.KitchenType;
import com.example.ProgrammingTechnology.model.OrderStatus;
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
    private final OrderStatusService orderStatusService;

    @Bean
    public int createRoles(){
        roleService.createRole(new Role("Client"));
        roleService.createRole(new Role("Courier"));
        roleService.createRole(new Role("Admin"));
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

    @Bean
    public int createKitchenType() {
        kitchenTypeService.createKitchenType(new KitchenType("Итальянская кухня"));
        kitchenTypeService.createKitchenType(new KitchenType("Французская кухня"));
        kitchenTypeService.createKitchenType(new KitchenType("Русская кухня"));
        kitchenTypeService.createKitchenType(new KitchenType("Тайская кухня"));
        kitchenTypeService.createKitchenType(new KitchenType("Китайская кухня"));
        kitchenTypeService.createKitchenType(new KitchenType("Кавказская кухня"));
        return 1;
    }

    @Bean
    public int createOrderStatus() {
        orderStatusService.createOrderStatus(new OrderStatus("В обработке"));
        orderStatusService.createOrderStatus(new OrderStatus("Принят"));
        orderStatusService.createOrderStatus(new OrderStatus("Готовится"));
        orderStatusService.createOrderStatus(new OrderStatus("Передан курьеру"));
        orderStatusService.createOrderStatus(new OrderStatus("Готов"));
        orderStatusService.createOrderStatus(new OrderStatus("Доставлен"));
        orderStatusService.createOrderStatus(new OrderStatus("Завершен"));
        return 1;
    }
}
