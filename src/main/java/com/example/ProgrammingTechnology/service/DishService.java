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

        return dishRepository.save(newDish);

    }

    //поиск блюда по id
    public Dish findDishById(Long id) {
        return dishRepository.findById(id).orElseThrow();
    }

    //поиск всех блюд
    public List<Dish> findDishes() {
        return dishRepository.findAll();
    }

    //изменение блюда
    public Dish updateDish(Dish upDish) {

        Dish dish = dishRepository.findById(upDish.getId()).orElseThrow();
        dishRepository.delete(dish);
        dishRepository.save(upDish);
        return upDish;


    }

    //изменение названия блюда
    public Dish updateName(Long id, String name) {
        if (dishRepository.findById(id).isPresent() && !name.isEmpty() && !name.isBlank()) {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dish.setName(name);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //изменение цены блюда
    public Dish updatePrice(Long id, Float price) {
        if (dishRepository.findById(id).isPresent() && price > 0) {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dish.setPrice(price);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //изменение калорийности блюда
    public Dish updateCalories(Long id, Float calories) {
        if (dishRepository.findById(id).isPresent() && calories > 0) {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dish.setPrice(calories);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //изменение веса блюда
    public Dish updateWeight(Long id, Float weight) {
        if (dishRepository.findById(id).isPresent() && weight > 0) {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dish.setPrice(weight);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //удаление блюда
    public void deleteDish(Dish dish) {

        dishRepository.delete(dish);

    }

    //удаление блюда по id
    public void deleteDishById(Long id) {
        if (dishRepository.findById(id).isPresent()) {
            dishRepository.deleteById(id);
        }
    }
}
