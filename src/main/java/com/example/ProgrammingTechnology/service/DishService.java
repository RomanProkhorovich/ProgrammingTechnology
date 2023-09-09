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

    //изменение названия блюда
    public Dish updateName(Long id,String name) {
        if(dishRepository.findById(id).isPresent() && !name.isEmpty() && !name.isBlank()) {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dish.setName(name);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //изменение цены блюда
    public Dish updatePrice(Long id, Float price) {
        if(dishRepository.findById(id).isPresent() && price>0) {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dish.setPrice(price);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //изменение калорийности блюда
    public Dish updateCalories(Long id, Float calories) {
        if(dishRepository.findById(id).isPresent() && calories>0) {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dish.setPrice(calories);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //изменение веса блюда
    public Dish updateWeight(Long id, Float weight) {
        if(dishRepository.findById(id).isPresent() && weight>0) {
            Dish dish = dishRepository.findById(id).orElseThrow();
            dish.setPrice(weight);
            dishRepository.save(dish);
            return dish;
        }
        return null;
    }

    //удаление блюда
    public void deleteDish(Dish dish) {
        if(dishRepository.findById(dish.getId()).isPresent()) {
            dishRepository.delete(dish);
        }
    }

    //удаление блюда по id
    public void deleteDishById(Long id) {
        if(dishRepository.findById(id).isPresent()) {
            dishRepository.deleteById(id);
        }
    }
}
