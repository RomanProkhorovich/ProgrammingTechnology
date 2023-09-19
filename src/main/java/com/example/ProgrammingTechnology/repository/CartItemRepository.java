package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByOrder(Order order);

    List<CartItem> findAllByDish(Dish dish);
}