package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}