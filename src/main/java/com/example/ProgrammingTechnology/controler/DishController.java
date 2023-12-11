package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.DishDto;
import com.example.ProgrammingTechnology.mapper.DishMapper;
import com.example.ProgrammingTechnology.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dishes")
public class DishController {
    private final DishService service;
    private final DishMapper mapper;


    @GetMapping("/{id}")
    public DishDto findById(@PathVariable Long id)
    {
        return mapper.toDto(service.findDishById(id));
    }

    @GetMapping
    public List<DishDto> findAll() {
        return mapper.toDtoList(service.findDishes());
    }

    @PostMapping
    public DishDto save(@RequestBody DishDto dto) {
        return mapper.toDto(service.createDish(mapper.toModel(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDishById(id);
    }

    @PutMapping()
    public DishDto changeName(@RequestParam Long id, @RequestParam String name) {
        return mapper.toDto(service.updateName(id, name));
    }


    @PutMapping("/price")
    public DishDto changePrice(@RequestParam Long id, @RequestParam Float price) {
        return mapper.toDto(service.updatePrice(id, price));
    }


    @PutMapping("/cal")
    public DishDto changeCalories(@RequestParam Long id, @RequestParam Float calories) {
        return mapper.toDto(service.updateCalories(id, calories));
    }


    @PutMapping("/weight")
    public DishDto changeWeight(@RequestParam Long id, @RequestParam Float weight) {
        return mapper.toDto(service.updateWeight(id, weight));
    }


}
