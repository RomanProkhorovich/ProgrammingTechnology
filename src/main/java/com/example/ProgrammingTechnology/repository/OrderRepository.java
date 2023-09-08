package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.OrderStatus;
import com.example.ProgrammingTechnology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
}