package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    //создание блюда
    public Dish createDish(Dish newDish) {
        //TODO fix
        if (newDish.getId() == null || dishRepository.findById(newDish.getId()).isEmpty())
            return dishRepository.save(newDish);
        throw new IllegalArgumentException();
    }

    //поиск блюда по id
    public Dish findDishById(Long id) {
        return dishRepository.findById(id).orElseThrow();
    }

    //поиск всех блюд
    public List<Dish> findDishes() {
        return dishRepository.findAll();
    }


    //изменение названия блюда
    public Dish updateName(Long id, String name) {
        Dish dish = dishRepository.findById(id).orElseThrow();
        if (name != null && !name.isEmpty() && !name.isBlank()) {
            dish.setName(name);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //изменение цены блюда
    public Dish updatePrice(Long id, Float price) {
        Dish dish = dishRepository.findById(id).orElseThrow();
        if (price != null && price > 0 && !price.isInfinite()) {
            dish.setPrice(price);
            dishRepository.save(dish);
            return dish;
        }
        //TODO: exc
        return null;
    }

    //изменение калорийности блюда
    public Dish updateCalories(Long id, Float calories) {
        Dish dish = dishRepository.findById(id).orElseThrow();
        if (calories != null && calories > 0 && !calories.isInfinite()) {
            dish.setPrice(calories);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //изменение веса блюда
    public Dish updateWeight(Long id, Float weight) {
        Dish dish = dishRepository.findById(id).orElseThrow();
        if (weight != null && weight > 0 && !weight.isInfinite()) {
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
        dishRepository.deleteById(id);
    }
}
