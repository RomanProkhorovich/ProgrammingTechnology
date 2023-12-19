package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.CartItem;
import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.model.Order;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DishesConfig {
    private final DishService service;
    private final OrderService orderService;
    private final UserService userService;
    private final RoleService roleService;
    private final ReceivingTypeService receivingTypeService;
    private final PasswordEncoder passwordEncoder;
    private final CartItemService cartItemService;

    @Bean
    void addData() throws IOException {
        Dish dish = new Dish();
        dish.setName("Кур очка");
        dish.setPrice(300f);
        dish.setCalories(130f);
        dish.setDescription("СОСИХУЙ");
        dish.setWeight(300f);
        File fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\pizza.jpg");
        byte[] fileContent = Files.readAllBytes(fi.toPath());
        dish.setPhoto(fileContent);
        service.createDish(dish);

        Dish cake = new Dish();
        cake.setDescription("Cake is a lie");
        cake.setName("Тортик");
        cake.setWeight(200f);
        cake.setPrice(500f);
        cake.setCalories(350f);
        fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\еда\\тьорт.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        cake.setPhoto(fileContent);
        service.createDish(cake);

        Dish fish = new Dish();
        fish.setPrice(440f);
        fish.setDescription("fish`ka");
        fish.setName("Риба на пару");
        fish.setWeight(450f);
        fish.setCalories(150f);
        fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\еда\\риба.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        fish.setPhoto(fileContent);
        service.createDish(fish);

        Dish kakleti = new Dish();
        kakleti.setPrice(330f);
        kakleti.setDescription("как лети");
        kakleti.setName("как каклети");
        kakleti.setWeight(350f);
        kakleti.setCalories(250f);
        fi = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\еда\\каклети.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        kakleti.setPhoto(fileContent);
        service.createDish(kakleti);
    }

    @Bean
    void addUsers() {
        User user = new User();
        user.setRole(roleService.findRoleByName("Client"));
        user.setPassword(passwordEncoder.encode("password"));
        user.setLastname("Чернов");
        user.setFirstname("Даниил");
        user.setSurname("Иванович");
        user.setEmail("cherni@example.ru");
        user.setAddress("г. Самара, ул. Мичурина, д. 375, кв. 19");
        user = userService.createUser(user);

        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Самовывоз"));
        order.setDeliveryTime(LocalDateTime.now());
        CartItem item = new CartItem(service.findDishById(1l), (byte) 4);
        item = cartItemService.createOrderDish(item);
        order.setCartItems(Set.of(item));
        order.setOrderTime(LocalDateTime.now());
        orderService.createOrder(order);

        order = new Order();
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер на дом"));
        order.setDeliveryTime(LocalDateTime.now());
        CartItem item2 = new CartItem(service.findDishById(2L), (byte) 1);
        item2 = cartItemService.createOrderDish(item2);
        order.setCartItems(Set.of(item2));
        order.setOrderTime(LocalDateTime.now());
        orderService.createOrder(order);
    }
}
