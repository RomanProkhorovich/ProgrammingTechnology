package com.example.ProgrammingTechnology.controler;


import com.example.ProgrammingTechnology.dto.RestaurantDto;
import com.example.ProgrammingTechnology.mapper.RestaurantMapper;
import com.example.ProgrammingTechnology.service.RestaurantService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantMapper mapper;
    private final RestaurantService service;

    @GetMapping
    public List<RestaurantDto> findAll() {
        return mapper.toDtoList(service.findRestaurants());
    }

    @GetMapping
    public List<RestaurantDto> findByName(@PathParam("name") String name) {
        return mapper.toDtoList(service.findRestaurantsByName(name));
    }

    @GetMapping
    public List<RestaurantDto> findByKitchen(@PathParam("kitchen_type") String type) {
        return mapper.toDtoList(service.findRestaurantsByKitchenType(type));
    }

    @GetMapping
    public RestaurantDto findById(@PathParam("id") Long id) {
        return mapper.toDto(service.findRestaurantById(id));
    }

}
