package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    //создание блюда
    public Dish createDish(Dish newDish) {
        if(dishRepository.findById(newDish.getId()).isEmpty()) {
            dishRepository.save(newDish);
        }
        return null;
    }

    //поиск блюда по id
    public Dish findDishById(Long id) {
        if(dishRepository.findById(id).isPresent()) {
            return dishRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //поиск всех блюд
    public List<Dish> findDishes() {
        return dishRepository.findAll();
    }

    //изменение блюда
    public Dish updateDish(Dish upDish) {
        if(dishRepository.findById(upDish.getId()).isPresent()) {
            dishRepository.findById(upDish.getId()).ifPresent(dishRepository::delete);
            dishRepository.save(upDish);
            return upDish;
        }
        return null;
    }

    //удаление блюда
    public void delete(Long id) {
        if(dishRepository.findById(id).isPresent()) {
            dishRepository.deleteById(id);
        }
    }
}
