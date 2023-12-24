package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.CartItem;
import com.example.ProgrammingTechnology.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final DishService dishService;


    //TODO: соединить методы create  update
    //создание заказанного блюда
    public CartItem createOrderDish(CartItem newOrderDish) {
        //if (cartItemRepository.findById(newOrderDish.getId()).isEmpty()) {
            return cartItemRepository.save(newOrderDish);
        //}
        //throw new IllegalArgumentException();
    }

    //поиск заказанного блюда по id
    public CartItem findOrderDish(Long id) {
        return cartItemRepository.findById(id).orElseThrow();
    }


    //TODO: использовать в сервисе заказов
    //поиск всех заказов у блюда
    public List<CartItem> findAllOrderByDish(Long dishId) {
        return cartItemRepository.findAllByDish(dishService.findDishById(dishId));
    }

    //поиск всех заказанных блюд
    public List<CartItem> findAllOrderDish() {
        return cartItemRepository.findAll();
    }

    //удаление заказанного блюда по id
    public void deleteOrderDishById(Long id) {
        cartItemRepository.deleteById(id);
    }

    //удаление заказанного блюда
    public void deleteOrderDish(CartItem orderDish) {
        cartItemRepository.delete(orderDish);
    }
}
