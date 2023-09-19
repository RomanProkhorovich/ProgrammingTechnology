package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.KitchenTypeDto;
import com.example.ProgrammingTechnology.mapper.KitchenTypeMapper;
import com.example.ProgrammingTechnology.service.KitchenTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kitchens")
public class KitchenTypeController {
    private final KitchenTypeService service;
    private final KitchenTypeMapper mapper;

    @GetMapping
    public List<KitchenTypeDto> findAll(){
        return mapper.toDtoList(service.findKitchenTypes());
    }
    @PostMapping
    public KitchenTypeDto save(@RequestBody KitchenTypeDto dto){
        return mapper.toDto(service.createKitchenType(mapper.toModel(dto)));
    }

    @GetMapping("/{name}")
    public KitchenTypeDto findByName(@PathVariable String name){
        return mapper.toDto(service.findKitchenTypeByName(name));
    }
}
