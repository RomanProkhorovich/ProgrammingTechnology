package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
    List<OrderDish> findAllByDishOrOrder(Long id);
}