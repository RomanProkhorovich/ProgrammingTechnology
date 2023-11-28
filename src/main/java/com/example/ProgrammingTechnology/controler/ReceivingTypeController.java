package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.ReceivingTypeDto;
import com.example.ProgrammingTechnology.mapper.ReceivingTypeMapper;
import com.example.ProgrammingTechnology.model.ReceivingType;
import com.example.ProgrammingTechnology.service.ReceivingTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receiving")
public class ReceivingTypeController {
    private final ReceivingTypeMapper mapper;
    private final ReceivingTypeService service;

    @GetMapping
    public List<ReceivingTypeDto> findAll() {
        return mapper.toDtoList(service.findReceivingTypes());
    }

    @GetMapping("/{name}")
    public ReceivingTypeDto findByName(@PathVariable String name) {
        return mapper.toDto(service.findReceivingTypeByName(name));
    }

    @PostMapping
    public ReceivingTypeDto create(@RequestBody ReceivingTypeDto dto) {
        ReceivingType receivingType = service.createReceivingType(mapper.toModel(dto));
        return mapper.toDto(receivingType);
    }
}
