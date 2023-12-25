package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.CreateOrderDto;
import com.example.ProgrammingTechnology.dto.OrderDto;
import com.example.ProgrammingTechnology.mapper.OrderMapper;
import com.example.ProgrammingTechnology.model.CartItem;
import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.security.SecurityHelper;
import com.example.ProgrammingTechnology.service.*;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    private final OrderStatusService orderStatusService;
    private final ReceivingTypeService receivingTypeService;
    private final AppMailSender mailSender;
    @Value("${mail.admin}")
    private String adminMail;

    @GetMapping("/addresses")
    public ResponseEntity<List<String>> findAllAddresses(@RequestParam(name = "id", required = false) Long id) {
        if (id == null)
            id = userService.findUserByEmail(SecurityHelper.getCurrentUser().getUsername()).getId();
        return ResponseEntity.ok(service.findAllAddressesByUserId(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody CreateOrderDto dto) {
        Order order = new Order();
        order.setCartItems(dto.getDishes().stream()
                .map(x -> new CartItem(dishService.findDishById(x.getId()), x.getCount()))
                .collect(Collectors.toSet()));

        User user;
        if (dto.getClientId() == null) {
            user = userService.findUserByEmail(SecurityHelper.getCurrentUser().getUsername());
        } else user = userService.findUserById(dto.getClientId());
        order.setClient(user);

        if (!dto.getAddress().isBlank())
            order.setAddress(dto.getAddress());
        else order.setAddress(order.getClient().getAddress());
        Long restaurantId = dto.getRestaurantId();
        order.setRestaurant(restaurantId == null ? null : restaurantService.findRestaurantById(restaurantId));
        order.setReceivingType(receivingTypeService.findReceivingTypeByName(dto.getReceivingType()));
        order.setOrderStatus(orderStatusService.findOrderStatusByName("В обработке"));
        service.createOrder(order);
        //TODO: отослать в ресторан
        mailSender.sendMessage(user.getEmail(), "Заказ", "Ваш заказ от" + new Date() + " Успешно оформлен ");

        mailSender.sendMessage(adminMail, "Заказ", "Оформлен новый заказ ");

        OrderDto orderDto = mapper.toDto(order);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseEntity<List<OrderDto>> fundAll(){
        return ResponseEntity.ok(mapper.toDtoList(service.findAll()));
    }
    @GetMapping
    public List<OrderDto> findAllByUser(@PathParam(value = "user_id") Long id,
                                        @PathParam(value = "actual") boolean actual) {
        List<Order> ordersByUser = service.findOrdersByUser(id, actual);
        return mapper.toDtoList(ordersByUser);
    }


    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable Long id) {
        return mapper.toDto(service.findOrderById(id));
    }

}
