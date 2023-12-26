package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.OrderStatus;
import com.example.ProgrammingTechnology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByClient(User user);
    //List<Order> findAllByCourier(User user);

    @Query("SELECT distinct address FROM app_order where client.id = ?1")
    List<String> getAllAddressesByUserId(Long id);

    @Query("SELECT order FROM app_order order where courier.id = ?1")
    List<Order> getAllByCourierId(Long id);
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
}