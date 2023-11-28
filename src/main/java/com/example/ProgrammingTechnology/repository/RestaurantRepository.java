package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.KitchenType;
import com.example.ProgrammingTechnology.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // List<Restaurant> finaAllByKitchenType(KitchenType kitchenType);

    List<Restaurant> findAllByName(String name);
}