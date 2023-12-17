package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.CreateOrderDto;
import com.example.ProgrammingTechnology.dto.OrderDto;
import com.example.ProgrammingTechnology.mapper.OrderMapper;
import com.example.ProgrammingTechnology.mapper.UserMapper;
import com.example.ProgrammingTechnology.model.CartItem;
import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.security.SecurityHelper;
import com.example.ProgrammingTechnology.service.*;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderMapper mapper;
    private final OrderService service;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final DishService dishService;
    private final UserMapper userMapper;
    private final ReceivingTypeService receivingTypeService;


    @PostMapping
    public OrderDto create(@RequestBody CreateOrderDto dto) {


        //TODO id client
        Order order = new Order();
        order.setCartItems(dto.getDishes().stream()
                .map(x-> new CartItem(dishService.findDishById(x.getId()), x.getCount()))
                .collect(Collectors.toSet()));
        if (dto.getClientId()==null){
            order.setClient(userService.findUserByEmail(SecurityHelper.getCurrentUser().getUsername()));
        }
        else order.setClient(userService.findUserById(dto.getClientId()));

        order.setDeliveryTime(dto.getDeliveryTime());
        if (dto.getAddress() != null)
            order.setAddress(dto.getAddress());
        else order.setAddress(order.getClient().getAddress());
        order.setRestaurant(restaurantService.findRestaurantById(dto.getRestaurantId()));
        order.setReceivingType(receivingTypeService.findReceivingTypeByName(dto.getReceivingTypeDto()));

        //TODO: отослать в ресторан

        return mapper.toDto(order);
    }

    @GetMapping
    public List<OrderDto> findAllByUser(@PathParam("user_id") Long id) {
        return mapper.toDtoList(service.findOrdersByUser(id));
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable Long id) {
        return mapper.toDto(service.findOrderById(id));
    }

}
