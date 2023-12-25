package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    //TODO: на проверку
    public Dish createOrUpdate(Dish dish) {
        if(dish.getName().isEmpty()
                || dish.getName().isBlank()
                || dish.getPrice()<0
                || dish.getCalories()<0
                || dish.getWeight()<=0) {
            throw new IllegalArgumentException();
        }
        if(dish.getId()==null) {
            return createDish(dish);
        }
        return updateDish(dish);
    }

    //TODO: на проверку
    public Dish updateDish(Dish upDish) {
        Dish dish = dishRepository.findById(upDish.getId()).orElseThrow();
        if(upDish.getName().isEmpty()
                || upDish.getName().isBlank()
                || upDish.getPrice()<0
                || upDish.getCalories()<0
                || upDish.getWeight()<=0) {
            throw new IllegalArgumentException();
        }
        return dishRepository.save(upDish);
    }

    //поиск блюда по id
    public Dish findDishById(Long id) {
        return dishRepository.findById(id).orElseThrow();
    }
    public List<Dish> saveAll(List<Dish> dishes){
        return dishes.stream().map(dishRepository::save).collect(Collectors.toList());
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
