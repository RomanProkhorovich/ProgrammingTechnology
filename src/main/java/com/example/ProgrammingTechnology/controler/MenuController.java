package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.DishDto;
import com.example.ProgrammingTechnology.dto.MenuDto;
import com.example.ProgrammingTechnology.mapper.DishMapper;
import com.example.ProgrammingTechnology.mapper.MenuMapper;
import com.example.ProgrammingTechnology.service.MenuService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuController {
    private final MenuMapper mapper;
    private final MenuService service;
    private final DishMapper dishMapper;

    @GetMapping("/{id}")
    public MenuDto find(@PathVariable Long id) {
        return mapper.toDto(service.findMenuById(id));
    }

    @GetMapping
    public List<MenuDto> findAll() {
        return mapper.toDtoList(service.findMenus());
    }

    @PostMapping
    public MenuDto create(@RequestBody MenuDto dto) {
        return mapper.toDto(service.createMenu(mapper.toModel(dto)));
    }

    @PutMapping("/{id}")
    public MenuDto addDish(@PathVariable Long id, @RequestBody DishDto dto) {
        return mapper.toDto(service.addDish(id, dishMapper.toModel(dto)));
    }

    @PutMapping()
    public MenuDto deleteDish(@PathParam("menu_id") Long id,
                              @PathParam("dish_id") Long dishId) {
        return mapper.toDto(service.deleteDish(id, dishId));
    }
}
