package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.OrderDto;
import com.example.ProgrammingTechnology.dto.UserDto;
import com.example.ProgrammingTechnology.mapper.OrderMapper;
import com.example.ProgrammingTechnology.mapper.UserMapper;
import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.service.OrderService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderMapper mapper;
    private final OrderService service;


    @PostMapping
    public OrderDto create(@RequestBody OrderDto dto){

        Order order = service.createOrder(mapper.toModel(dto));

        //TODO: отослать в ресторан

        return mapper.toDto(order);
    }

    @GetMapping
    public List<OrderDto> findAllByUser(@PathParam("user_id") Long id){
        return mapper.toDtoList(service.findOrdersByUser(id));
    }
    @GetMapping
    public OrderDto findById(@PathParam("id") Long id){
        return mapper.toDto(service.findOrderById(id));
    }

}
