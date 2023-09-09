package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.OrderStatus;
import com.example.ProgrammingTechnology.repository.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    //создание статуса
    public OrderStatus createOrderStatus(OrderStatus newOrderStatus) {
        if(orderStatusRepository.findById(newOrderStatus.getId()).isEmpty()) {
            orderStatusRepository.save(newOrderStatus);
            return newOrderStatus;
        }
        return null;
    }

    //поиск статуса по id
    public OrderStatus findOrderStatusById(Long id) {
        if(orderStatusRepository.findById(id).isPresent()) {
            return orderStatusRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //поиск статуса по названию
    public Optional<OrderStatus> findOrderStatusByName(String name) {
        if(orderStatusRepository.findByName(name).isPresent()) {
            return orderStatusRepository.findByName(name);
        }
        return null;
    }

    //поиск всех статусов
    public List<OrderStatus> findOrderStatuses() {
        return orderStatusRepository.findAll();
    }
}
