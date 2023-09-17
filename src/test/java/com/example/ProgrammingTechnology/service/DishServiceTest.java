package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.repository.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {

    @Mock
    DishRepository repo;

    @InjectMocks
    DishService dishService;
    @Test
    void createDish() {
        Dish dish=new Dish();
        dish.setCalories(100f);
        dish.setName("Fries");
        dish.setWeight(300f);
        dish.setPrice(300f);


        Dish saved=new Dish();
        saved.setCalories(100f);
        saved.setName("Fries");
        saved.setWeight(300f);
        saved.setPrice(300f);
        saved.setId(1L);
        Mockito.when(repo.save(dish)).thenReturn(saved);
        assertEquals(1L,dishService.createDish(dish).getId());
    }

    @Test
    void findDishByIdExceptionExpected() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(Exception.class,()->dishService.findDishById(1L));
    }

    @Test
    void findDishById() {
        Dish dish=new Dish();
        dish.setCalories(100f);
        dish.setName("Fries");
        dish.setWeight(300f);
        dish.setPrice(300f);
        dish.setId(1L);

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(dish));
        assertEquals(dish,dishService.findDishById(1L));
    }

    @Test
    void findDishes() {
        Dish fries=new Dish();
        fries.setCalories(100f);
        fries.setName("Fries");
        fries.setWeight(300f);
        fries.setPrice(300f);

        Dish fish=new Dish();
        fish.setName("fish");

        Mockito.when(repo.findAll()).thenReturn(List.of(fish,fries));
        var a=dishService.findDishes();
        assertEquals(2,a.size());
        assertTrue(a.stream().anyMatch(x->x.getName().equals("Fries")));

        assertTrue(a.stream().anyMatch(x->x.getName().equals("fish")));
    }

}