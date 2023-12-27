package com.example.ProgrammingTechnology.controler;


import com.example.ProgrammingTechnology.dto.RestaurantDto;
import com.example.ProgrammingTechnology.mapper.RestaurantMapper;
import com.example.ProgrammingTechnology.service.RestaurantService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantMapper mapper;
    private final RestaurantService service;


    @PostMapping("/all")
    public ResponseEntity<List<RestaurantDto>> saveAll(List<RestaurantDto> restaurantDtoList) {
        return ResponseEntity.ok(mapper.toDtoList(service.saveAll(mapper.toModelList(restaurantDtoList))));
    }

    @GetMapping("/all")
    @Transactional
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public List<RestaurantDto> findAll() {
        return mapper.toDtoList(service.findRestaurants());
    }

    @GetMapping("/byName")
    public List<RestaurantDto> findByName(@PathParam("name") String name) {
        return mapper.toDtoList(service.findRestaurantsByName(name));
    }

    @GetMapping("/btKitchenType")
    public List<RestaurantDto> findByKitchen(@PathParam("kitchen_type") String type) {
        return mapper.toDtoList(service.findRestaurantsByKitchenType(type));
    }

    @GetMapping("/byId")
    public RestaurantDto findById(@PathVariable("id") Long id) {
        return mapper.toDto(service.findRestaurantById(id));
    }

    @PostMapping
    public RestaurantDto create(@RequestBody RestaurantDto restaurantDto) {
        return mapper.toDto(service.createRestaurant(mapper.toModel(restaurantDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteRestaurantById(id);
    }

}
