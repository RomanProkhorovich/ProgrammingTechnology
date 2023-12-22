package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.repository.OrderRepository;
import com.example.ProgrammingTechnology.security.SecurityHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@CrossOrigin(origins = "http://localhost:63342", maxAge = 3600)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final OrderStatusService orderStatusService;
    private final ReceivingTypeService receivingTypeService;

    //TODO: сделать проверку на количество мест, если заказали в зале
    //TODO: адрес по дефолту это адрес у клиента
    //создание заказа
    public Order createOrder(Order newOrder) {
        if (newOrder.getId() != null && orderRepository.findById(newOrder.getId()).isPresent()) {
            throw new IllegalArgumentException();
        }
        if (newOrder.getAddress() != null)
            return orderRepository.save(newOrder);
        if (newOrder.getClient() == null)
            newOrder.setClient(userService
                    .findUserByEmail(SecurityHelper.getCurrentUser().getUsername()));

        if (newOrder.getClient().getAddress() != null)
            newOrder.setAddress(newOrder.getClient().getAddress());

        return orderRepository.save(newOrder);
    }

    //поиск заказа по id
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    //TODO: сделать разные контроллеры для клиента и курьера
    //поиск заказов по курьеру
    @Transactional
    public List<Order> findOrdersByUser(Long userId, Boolean actual) {
        List<Order> allByClient = orderRepository.findAllByClient(userService.findUserById(userId));
        if (actual != null && actual)
            allByClient = allByClient.stream()
                    .filter(x -> !Objects.equals(x.getOrderStatus().getName(), "Завершен") &&
                            !Objects.equals(x.getOrderStatus().getName(), "Доставлен"))
                    .collect(Collectors.toList());
        return allByClient;
    }

    //поиск заказов по статусу
    public List<Order> findOrderByOrderStatus(Long orderStatusId) {
        return orderRepository.findAllByOrderStatus(orderStatusService.findOrderStatusById(orderStatusId));
    }


    //изменение статуса заказа
    public Order updateOrderStatus(Long id, Long orderStatusId) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setOrderStatus(orderStatusService.findOrderStatusById(orderStatusId));
        return orderRepository.save(order);
    }

    //изменение времени доставки заказа
    public Order updateDeliveryTime(Long id, LocalDateTime upDeliveryTime) {
        if (upDeliveryTime.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException();
        Order order = orderRepository.findById(id).orElseThrow();
        order.setDeliveryTime(upDeliveryTime);
        return orderRepository.save(order);
    }

    //изменение курьера заказа
    public Order updateCourier(Long id, Long courierId) {
        Order order = orderRepository.findById(id).orElseThrow();
        User userById = userService.findUserById(courierId);
        if (userById.getRole().getName().equals("Courier")) {
            order.setCourier(userById);
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException();
    }

    //изменение клиента заказа
    public Order updateClient(Long id, Long clientId) {
        Order order = orderRepository.findById(id).orElseThrow();
        User userById = userService.findUserById(clientId);
        if (userById.getRole().equals("Client")) {
            order.setClient(userById);
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException();
    }

    //изменение способа получения заказа
    public Order updateReceivingType(Long id, String name) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setReceivingType(receivingTypeService.findReceivingTypeByName(name));
        return orderRepository.save(order);
    }

    //изменение адреса заказа
    public Order updateAddress(Long id, String address) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (address != null && !address.isEmpty() && !address.isBlank()) {
            order.setAddress(address);
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException();
    }

    //удаление заказа по id
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    //удаление заказа
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}
