package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.OrderStatus;
import com.example.ProgrammingTechnology.repository.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    //создание статуса
    public OrderStatus createOrderStatus(OrderStatus newOrderStatus) {
        if (orderStatusRepository.findById(newOrderStatus.getId()).isEmpty()) {
            return orderStatusRepository.save(newOrderStatus);
        }
        throw new IllegalArgumentException();
    }

    //поиск статуса по id
    public OrderStatus findOrderStatusById(Long id) {
        return orderStatusRepository.findById(id).orElseThrow();
    }

    //поиск статуса по названию
    public OrderStatus findOrderStatusByName(String name) {
        return orderStatusRepository.findByName(name).orElseThrow();
    }

    //поиск всех статусов
    public List<OrderStatus> findOrderStatuses() {
        return orderStatusRepository.findAll();
    }
}
