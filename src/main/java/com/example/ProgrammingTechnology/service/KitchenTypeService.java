package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.KitchenType;
import com.example.ProgrammingTechnology.repository.KitchenTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitchenTypeService {
    private final KitchenTypeRepository kitchenTypeRepository;

    //создание вида кухни
    public KitchenType createKitchenType(KitchenType newKitchenType) {
        if(kitchenTypeRepository.findById(newKitchenType.getId()).isEmpty()) {
            return kitchenTypeRepository.save(newKitchenType);
        }
        throw new IllegalArgumentException();
    }

    //поиск вида кухни по id
    public KitchenType findKitchenTypeById(Long id) {
        return kitchenTypeRepository.findById(id).orElseThrow();
    }

    //поиск вида кухни по названию
    public KitchenType findKitchenTypeByName(String name) {
        return kitchenTypeRepository.findByName(name).orElseThrow();
    }

    //поиск всех видов кухонь
    public List<KitchenType> findKitchenTypes() {
        return kitchenTypeRepository.findAll();
    }
}
