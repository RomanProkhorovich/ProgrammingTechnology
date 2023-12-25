package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.exception.AttributeException;
import com.example.ProgrammingTechnology.model.CartItem;
import com.example.ProgrammingTechnology.model.Dish;
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

    //TODO: на проверку
    public CartItem createOrUpdate(CartItem cartItem) {
        if (cartItem.getId() == null) {
            return createOrderDish(cartItem);
        }
        return updateCartItem(cartItem);
    }

    public CartItem updateCartItem(CartItem upCartItem) {
        CartItem cartItem = cartItemRepository.findById(upCartItem.getId()).orElseThrow(() -> new AttributeException("", "id", upCartItem.getId()));
        Dish dish = dishService.findDishById(upCartItem.getId());
        if (upCartItem.getCount() >= 0 && dish != null) {
            return cartItemRepository.save(upCartItem);
        }
        throw new IllegalArgumentException();
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
