package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.ReceivingType;
import com.example.ProgrammingTechnology.repository.ReceivingTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceivingTypeService {
    private final ReceivingTypeRepository receivingTypeRepository;

    //создание способа получения заказа
    public ReceivingType createReceivingType(ReceivingType newReceivingType) {
        if (receivingTypeRepository.findById(newReceivingType.getId()).isEmpty()) {
            return receivingTypeRepository.save(newReceivingType);
        }
        throw new IllegalArgumentException();
    }

    //поиск способа получения заказа по id
    public ReceivingType findReceivingTypeById(Long id) {
        return receivingTypeRepository.findById(id).orElseThrow();
    }

    //поиск способа получения заказа по названию
    public ReceivingType findReceivingTypeByName(String name) {
        return receivingTypeRepository.findByName(name).orElseThrow();
    }

    //поиск всех способов получения заказа
    public List<ReceivingType> findReceivingTypes() {
        return receivingTypeRepository.findAll();
    }
}
