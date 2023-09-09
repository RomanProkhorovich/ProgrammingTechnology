package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.OrderStatus;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderStatusService orderStatusService;

    //TODO: сделать проверку на количество мест, если заказали в зале
    //создание заказа
    public Order createOrder(Order newOrder) {
        if(orderRepository.findById(newOrder.getId()).isEmpty()) {
            orderRepository.save(newOrder);
            return newOrder;
        }
        return null;
    }

    //поиск заказа по id
    public Order findOrderById(Long id) {
        if(orderRepository.findById(id).isPresent()) {
            return orderRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //поиск заказов по курьеру
    public List<Order> findOrdersByCourier(Long courierId) {
        if(userService.findUserById(courierId)!=null) {
            User courier = userService.findUserById(courierId);
            if (courier.getRole().getName().equals("Courier")) {
                return orderRepository.findAllByUser(courier);
            }
            //return null;
        }
        return null;
    }

    //TODO: допилить клиента в заказе, чтобы сделать поиск по клиенту
    //поиск заказов по клиенту
    //public List<Order> findOrderByClient

    //поиск заказов по статусу
    public List<Order> findOrderByOrderStatus(Long orderStatusId) {
        if(orderStatusService.findOrderStatusById(orderStatusId)!=null) {
            OrderStatus orderStatus = orderStatusService.findOrderStatusById(orderStatusId);
            return orderRepository.findAllByOrderStatus(orderStatus);
            //return null;
        }
        return null;
    }

    //изменение времени заказа
    public Order updateOrderTime(Long id, LocalDateTime upOrderTime) {
        Order order = orderRepository.findById(id).orElseThrow();
        if(orderRepository.findById(id).isPresent()) {
            order.setOrderTime(upOrderTime);
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    //изменение статуса заказа
    public Order updateOrderStatus(Long id, Long orderStatusId) {
        Order order = orderRepository.findById(id).orElseThrow();
        if(orderRepository.findById(id).isPresent() && orderStatusService.findOrderStatusById(orderStatusId)!=null) {
            order.setOrderStatus(orderStatusService.findOrderStatusById(orderStatusId));
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    //изменение времени доставки заказа
    public Order updateDeliveryTime(Long id, LocalDateTime upDeliveryTime) {
        Order order = orderRepository.findById(id).orElseThrow();
        if(orderRepository.findById(id).isPresent()) {
            order.setDeliveryTime(upDeliveryTime);
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    //изменение курьера заказа
    public Order updateCourier(Long id, Long courierId) {
        Order order = orderRepository.findById(id).orElseThrow();
        if(orderRepository.findById(id).isPresent() && userService.findUserById(courierId)!=null) {
            order.setCourier(userService.findUserById(courierId));
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    //TODO: прописать изменение клиента заказа
    //TODO: прописать изменение способа получения заказа (самовывоз, доставка, в зале)
    //TODO: адрес по дефолту это адрес у клиента

    //удаление заказа

}
