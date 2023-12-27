package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.service.OrderService;
import com.example.ProgrammingTechnology.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Secured("hasAuthority('Manager')")
@RestController
@RequestMapping("api/v1/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final OrderService orderService;
    private final UserService userService;

    @PutMapping("/setCourier")
    public ResponseEntity<?> setCourier(@RequestParam("order_id") Long orderId,
                                       @RequestParam("courier_id") Long courierId ){
        User courier = userService.findUserById(courierId);
        if (!Objects.equals(courier.getRole().getName(), "Courier"))
            return ResponseEntity.badRequest().body("Не курьер");
        Order order = orderService.findOrderById(orderId);
        order.setCourier(courier);
        orderService.updateOrder(order);
        return ResponseEntity.ok("Успешный успех");
    }
}
