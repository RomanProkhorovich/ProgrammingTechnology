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
    private final ReceivingTypeService receivingTypeService;

    //TODO: сделать проверку на количество мест, если заказали в зале
    //TODO: адрес по дефолту это адрес у клиента
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

    //поиск заказов по клиенту
    public List<Order> findOrdersByClient(Long clientId) {
        if(userService.findUserById(clientId)!=null) {
            User client = userService.findUserById(clientId);
            if(client.getRole().getName().equals("Client")) {
                return orderRepository.findAllByUser(client);
            }
        }
        return null;
    }

    //поиск заказов по статусу
    public List<Order> findOrderByOrderStatus(Long orderStatusId) {
        if(orderStatusService.findOrderStatusById(orderStatusId)!=null) {
            OrderStatus orderStatus = orderStatusService.findOrderStatusById(orderStatusId);
            return orderRepository.findAllByOrderStatus(orderStatus);
            //return null;
        }
        return null;
    }

    //изменение времени создания заказа
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

    //TODO: сделать проверку: новое значение времени доставки не должно быть раньше времени создания заказа
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
        if(orderRepository.findById(id).isPresent()
                && userService.findUserById(courierId)!=null
                && userService.findUserById(courierId).getRole().equals("Courier")) {
            order.setCourier(userService.findUserById(courierId));
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    //изменение клиента заказа
    public Order updateClient(Long id, Long clientId) {
        Order order = orderRepository.findById(id).orElseThrow();
        if(orderRepository.findById(id).isPresent()
                && userService.findUserById(clientId)!=null
                && userService.findUserById(clientId).getRole().equals("Client")) {
            order.setClient(userService.findUserById(clientId));
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    //изменение способа получения заказа
    public Order updateReceivingType(Long id, String name) {
        if(orderRepository.findById(id).isPresent() && receivingTypeService.findReceivingTypeByName(name).isPresent()) {
            Order order = orderRepository.findById(id).orElseThrow();
            order.setReceivingType(receivingTypeService.findReceivingTypeByName(name).orElseThrow());
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    //изменение адреса заказа
    public Order updateAddress(Long id, String address) {
        if(orderRepository.findById(id).isPresent() && !address.isEmpty() && !address.isBlank()) {
            Order order = orderRepository.findById(id).orElseThrow();
            order.setAddress(address);
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    //удаление заказа по id
    public void deleteOrderById(Long id) {
        if(orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
        }
    }

    //удаление заказа
    public void deleteOrder(Order order) {
        if(orderRepository.findById(order.getId()).isPresent()) {
            orderRepository.delete(order);
        }
    }
}
