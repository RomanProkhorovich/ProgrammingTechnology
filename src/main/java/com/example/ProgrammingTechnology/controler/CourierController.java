package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.OrderDto;
import com.example.ProgrammingTechnology.mapper.OrderMapper;
import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.service.OrderService;
import com.example.ProgrammingTechnology.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/courier")
@RestController
@RequiredArgsConstructor
@Secured("hasAuthority('Courier')")
public class CourierController {
    private final OrderService orderService;
    private final OrderStatusService statusService;
    private final OrderMapper mapper;

    private final static String READY = "Готов";
    private final static String COURIER = "Передан курьеру";
    private final static String END = "Доставлен";

    @PutMapping()
    private ResponseEntity<String> changeStatus(@RequestParam(name = "id") Long id) {
        Order order = orderService.findOrderById(id);
        String status = order.getOrderStatus().getName();
        if (status.equals(READY)){
            order.setOrderStatus(statusService.findOrderStatusByName(COURIER));
            orderService.createOrUpdate(order);
            return ResponseEntity.ok(COURIER);
        }
        if (status.equals(COURIER)){
            order.setOrderStatus(statusService.findOrderStatusByName(END));
            orderService.createOrUpdate(order);
            return ResponseEntity.ok(END);
        }
        return ResponseEntity.badRequest().body("Курьер не может редактировать данный заказ");
    }
    @GetMapping()
    public ResponseEntity<List<OrderDto>> findForCourier(@RequestParam(name="id",required = true) Long id, @RequestParam(name = "actual") Boolean actual){
        return ResponseEntity.ok(mapper.toDtoList(orderService.findAllByCourier(id,actual)));
    }
}
