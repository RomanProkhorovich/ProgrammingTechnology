package com.example.ProgrammingTechnology.controler;


import com.example.ProgrammingTechnology.dto.RestaurantDto;
import com.example.ProgrammingTechnology.mapper.RestaurantMapper;
import com.example.ProgrammingTechnology.model.Restaurant;
import com.example.ProgrammingTechnology.service.RestaurantService;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantMapper mapper;
    private final RestaurantService service;


    @GetMapping("/addresses")
    public ResponseEntity<List<String>> findAddresses(){
        List<String> list = findAll().stream().map(x -> x.getAddress() + " " + x.getName()).toList();
        return ResponseEntity.ok(list);
    }
    @PostMapping("/all")
    public ResponseEntity<List<RestaurantDto>> saveAll(List<RestaurantDto> restaurantDtoList) {
        return ResponseEntity.ok(mapper.toDtoList(service.saveAll(mapper.toModelList(restaurantDtoList))));
    }

    @GetMapping("/all")
    @Transactional
    //@PreAuthorize("hasAnyAuthority('Admin','Manager')")
    //@Secured("permitAll()")
    public List<RestaurantDto> findAll() {
        List<Restaurant> restaurants = service.findRestaurants();
        List<RestaurantDto> dtoList = mapper.toDtoList(restaurants);
        return dtoList;
    }

    @GetMapping("/byName")
    public List<RestaurantDto> findByName(@PathParam("name") String name) {
        return mapper.toDtoList(service.findRestaurantsByName(name));
    }

    @GetMapping("/byKitchenType")
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
