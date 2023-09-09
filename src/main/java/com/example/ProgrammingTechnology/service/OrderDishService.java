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
        if(orderDishRepository.findById(newOrderDish.getId()).isEmpty()) {
            orderDishRepository.save(newOrderDish);
            return newOrderDish;
        }
        return null;
    }

    //поиск заказанного блюда по id
    public OrderDish findOrderDish(Long id) {
        if(orderDishRepository.findById(id).isPresent()) {
            return orderDishRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //TODO: ХЗ ?поиск блюд у заказа и поиск заказов у блюда?
    //поиск всех заказов у блюда
    public List<OrderDish> findAllOrderDishByDish(Long dishId) {
        if(dishService.findDishById(dishId)!=null) {
            Dish dish = dishService.findDishById(dishId);
            return orderDishRepository.findAllByDishOrOrder(dishId);
        }
        return null;
    }

    //TODO: hz
    //поиск всех блюд у заказа
    public List<OrderDish> findAllOrderDishByOrder(Long orderId) {
        if(orderService.findOrderById(orderId)!=null) {
            Order order = orderService.findOrderById(orderId);
            return orderDishRepository.findAllByDishOrOrder(orderId);
        }
        return null;
    }

    //поиск всех заказанных блюд
    public List<OrderDish> findAllOrderDish() {
        return orderDishRepository.findAll();
    }

    //удаление заказанного блюда по id
    public void deleteOrderDishById(Long id) {
        if(orderDishRepository.findById(id).isPresent()) {
            orderDishRepository.deleteById(id);
        }
    }

    //удаление заказанного блюда
    public void deleteOrderDish(OrderDish orderDish) {
        if(orderDishRepository.findById(orderDish.getId()).isPresent()) {
            orderDishRepository.delete(orderDish);
        }
    }
}
