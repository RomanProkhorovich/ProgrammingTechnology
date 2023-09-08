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
            kitchenTypeRepository.save(newKitchenType);
            return newKitchenType;
        }
        return null;
    }

    //поиск вида кухни по id
    public KitchenType findKitchenTypeById(Long id) {
        if(kitchenTypeRepository.findById(id).isPresent()) {
            return kitchenTypeRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //поиск вида кухни по названию
    public Optional<KitchenType> findKitchenTypeByName(String name) {
        if(kitchenTypeRepository.findByName(name).isPresent()) {
            return kitchenTypeRepository.findByName(name);
        }
        return null;
    }

    //поиск всех видов кухонь
    public List<KitchenType> findKitchenTypes() {
        return kitchenTypeRepository.findAll();
    }

    //изменение вида кухни
    public KitchenType updateKitchenType(KitchenType upKitchenType) {
        if(kitchenTypeRepository.findById(upKitchenType.getId()).isPresent()) {
            kitchenTypeRepository.findById(upKitchenType.getId()).ifPresent(kitchenTypeRepository::delete);
            kitchenTypeRepository.save(upKitchenType);
            return upKitchenType;
        }
        return null;
    }

    //удаление вида кухни по id
    public void deleteKitchenTypeById(Long id) {
        if(kitchenTypeRepository.findById(id).isPresent()) {
            kitchenTypeRepository.deleteById(id);
        }
    }

    //удаление вида кухни по названию
    public void deleteKitchenTypeByName(String name) {
        if(kitchenTypeRepository.findByName(name)!=null) {
            kitchenTypeRepository.deleteByName(name);
        }
    }
}
