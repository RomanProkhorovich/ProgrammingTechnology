package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.OrderDish;
import com.example.ProgrammingTechnology.repository.OrderDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDishService {
    private final OrderDishRepository orderDishRepository;
    private final DishService dishService;
    private final OrderService orderService;

    //создание заказанного блюда
    public OrderDish createOrderDish(OrderDish newOrderDish) {
        if (orderDishRepository.findById(newOrderDish.getId()).isEmpty()) {
            return orderDishRepository.save(newOrderDish);
        }
        throw new IllegalArgumentException();
    }

    //поиск заказанного блюда по id
    public OrderDish findOrderDish(Long id) {
        return orderDishRepository.findById(id).orElseThrow();
    }


    //TODO: использовать в сервисе заказов
    //поиск всех заказов у блюда
    public List<OrderDish> findAllOrderByDish(Long dishId) {
        return orderDishRepository.findAllByDish(dishService.findDishById(dishId));
    }

    //TODO: использовать в сервисе блюд
    //поиск всех блюд у заказа
    public List<OrderDish> findAllDishByOrder(Long orderId) {
        return orderDishRepository.findAllByOrder(orderService.findOrderById(orderId));
    }

    //поиск всех заказанных блюд
    public List<OrderDish> findAllOrderDish() {
        return orderDishRepository.findAll();
    }

    //удаление заказанного блюда по id
    public void deleteOrderDishById(Long id) {
        orderDishRepository.deleteById(id);
    }

    //удаление заказанного блюда
    public void deleteOrderDish(OrderDish orderDish) {
        orderDishRepository.delete(orderDish);
    }
}
