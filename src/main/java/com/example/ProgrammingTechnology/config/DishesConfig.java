package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.*;
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
    private final RestaurantService restaurantService;
    private final DishService service;
    private final OrderService orderService;
    private final UserService userService;
    private final RoleService roleService;
    private final ReceivingTypeService receivingTypeService;
    private final PasswordEncoder passwordEncoder;
    private final CartItemService cartItemService;
    private final MenuService menuService;

    @Bean
    int addData() throws IOException {
        Dish dish = new Dish();
        dish.setName("Пицца");
        dish.setPrice(300f);
        dish.setCalories(130f);
        dish.setDescription("Вкусная пицца как у бабули");
        dish.setWeight(300f);
        File fi = new File("src//main//resources//еда//pizza.jpg");
        byte[] fileContent = Files.readAllBytes(fi.toPath());
        dish.setPhoto(fileContent);
        service.createDish(dish);

        Dish cake = new Dish();
        cake.setDescription("Блинчики с ягодами(вроде)");
        cake.setName("Блинчики");
        cake.setWeight(200f);
        cake.setPrice(500f);
        cake.setCalories(350f);
        fi = new File("src//main//resources//еда//тьорт.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        cake.setPhoto(fileContent);
        service.createDish(cake);

        Dish fish = new Dish();
        fish.setPrice(440f);
        fish.setDescription("Рыба");
        fish.setName("Риба на пару");
        fish.setWeight(450f);
        fish.setCalories(150f);
        fi = new File("src//main//resources//еда//риба.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        fish.setPhoto(fileContent);
        service.createDish(fish);

        Dish kakleti = new Dish();
        kakleti.setPrice(330f);
        kakleti.setDescription("Мясные каклети с мясом из мяса мяса(свинина и говядина " +
                "и может быть немного домашних питомцев, надо спросить у повара), это какой то обычный текст, чтобы набить длину и я не знаю шо такое тут такое это");
        kakleti.setName("Котлеты");
        kakleti.setWeight(350f);
        kakleti.setCalories(250f);
        fi = new File("src//main//resources//еда//каклети.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        kakleti.setPhoto(fileContent);
        service.createDish(kakleti);

        Dish olive = new Dish();
        olive.setPrice(160f);
        olive.setDescription("С цыпленком");
        olive.setName("Оливье");
        olive.setWeight(160f);
        olive.setCalories(300f);
        fi = new File("src//main//recources//еда//оливье.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        olive.setPhoto(fileContent);
        service.createDish(olive);

        Dish grib = new Dish();
        grib.setPrice(160f);
        grib.setDescription("Идеально подходит для диетического питания");
        grib.setName("Грибной суп");
        grib.setWeight(250f);
        grib.setCalories(200f);
        fi = new File("src//main//recources//еда//грибной_суп.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        grib.setPhoto(fileContent);
        service.createDish(grib);

        Dish skumb = new Dish();
        skumb.setPrice(310f);
        skumb.setDescription("С пастой казаречче");
        skumb.setName("Скумбрия на гриле");
        skumb.setWeight(290f);
        skumb.setCalories(500f);
        fi = new File("src//main//recources//еда//скумбрия_на_гриле.jpeg");
        fileContent = Files.readAllBytes(fi.toPath());
        skumb.setPhoto(fileContent);
        service.createDish(skumb);

        Dish sir = new Dish();
        sir.setPrice(190f);
        sir.setDescription("С сухариками");
        sir.setName("Сырный суп");
        sir.setWeight(245f);
        sir.setCalories(150f);
        fi = new File("src//main//recources//еда//сырный_суп.jpeg");
        fileContent = Files.readAllBytes(fi.toPath());
        sir.setPhoto(fileContent);

        Dish sirniki = new Dish();
        sirniki.setPrice(330f);
        sirniki.setDescription("Со сметаной и клубничным вареньем");
        sirniki.setName("Сырники");
        sirniki.setWeight(185f);
        sirniki.setCalories(400f);
        fi = new File("src//main//recources//еда//сырники.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        sirniki.setPhoto(fileContent);

        Dish brus = new Dish();
        brus.setPrice(600f);
        brus.setDescription("С креветками");
        brus.setName("Брускетты");
        brus.setWeight(200f);
        brus.setCalories(200f);
        fi = new File("src//main//recources//еда//брускетты.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        brus.setPhoto(fileContent);

        Dish cezar = new Dish();
        cezar.setPrice(480f);
        cezar.setDescription("С цыпленком");
        cezar.setName("Цезарь");
        cezar.setWeight(230f);
        cezar.setCalories(310f);
        fi = new File("src//main//recources//еда//цезарь.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        cezar.setPhoto(fileContent);

        Dish borsch = new Dish();
        borsch.setPrice(295f);
        borsch.setDescription("Со сметаной");
        borsch.setName("Борщ");
        borsch.setWeight(330f);
        borsch.setCalories(230f);
        fi = new File("src//main//recources//еда//борщ.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        borsch.setPhoto(fileContent);

        Dish lazan = new Dish();
        lazan.setPrice(340f);
        lazan.setDescription("Мясная");
        lazan.setName("Лазанья");
        lazan.setWeight(220f);
        lazan.setCalories(500f);
        fi = new File("src//main//recources//еда//лазанья.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        lazan.setPhoto(fileContent);

        Dish palki = new Dish();
        palki.setPrice(380f);
        palki.setDescription("С фирменным соусом");
        palki.setName("Сырные палочки");
        palki.setWeight(260f);
        palki.setCalories(600f);
        fi = new File("src//main//recources//еда//сырные_палочки.jpeg");
        fileContent = Files.readAllBytes(fi.toPath());
        palki.setPhoto(fileContent);



        Menu menu = new Menu();
        menu.setDishes(Set.of(kakleti,fish,cake,dish, borsch, brus, grib, lazan, olive, skumb, sirniki, palki, sir, cezar));
        menu.setApproval(LocalDateTime.now());
        menu.setActual(true);
        menu=menuService.createMenu(menu);
/*
        Restaurant rest = new Restaurant();
        rest.setName("Пизда кузи миши");
        rest.setAddress("г. Самара, ул. Черемшанская 111");
        restaurantService.createRestaurant(rest);*/
        return 1;
    }

    @Bean
    int addUsers() {
        User user = new User();
        user.setRole(roleService.findRoleByName("Client"));
        user.setPassword(passwordEncoder.encode("password"));
        user.setLastname("Чернов");
        user.setFirstname("Даниил");
        user.setSurname("Иванович");
        user.setEmail("cherni@example.ru");
        user.setAddress("г. Самара, ул. Мичурина, д. 375, кв. 19");
        user.setPhone("89095492710");
        user = userService.createUser(user);
        User cur = User.builder()
                .phone("89274810058")
                .firstname("Абуля")
                .surname("Иванович")
                .lastname("Курьеров")
                .role(roleService.findRoleByName("Courier"))
                .email("abul.nadel@example.ru")
                .password(passwordEncoder.encode("hehe"))
                .build();
        cur = userService.createUser(cur);

        User admin = User.builder()
                .phone("89871029411")
                .firstname("Олег")
                .surname("Челикович")
                .lastname("Анимов")
                .role(roleService.findRoleByName("Admin"))
                .email("adik.tripa@example.ru")
                .password(passwordEncoder.encode("loski"))
                .build();
        admin = userService.createUser(admin);

        User manager = User.builder()
                .phone("89991105928")
                .firstname("Кузя")
                .surname("Михайлович")
                .lastname("Мужыков")
                .role(roleService.findRoleByName("Manager"))
                .email("kyza.misha@example.ru")
                .password(passwordEncoder.encode("vrotee"))
                .build();
        userService.createOrUpdate(manager);


       /* user.setId(null);
        userService.createUser(user);*/

        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Самовывоз"));
        order.setDeliveryTime(LocalDateTime.now());
        CartItem item = new CartItem(service.findDishById(1l), (byte) 4);
        item = cartItemService.createOrderDish(item);
        order.setCartItems(Set.of(item));
        order.setOrderTime(LocalDateTime.now());
        var a = orderService.createOrder(order);

        order.setAddress("адрес1");
        order.setId(null);
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Самовывоз"));
        order.setDeliveryTime(LocalDateTime.now());
        //CartItem item = new CartItem(service.findDishById(1l), (byte) 4);
        item.setId(null);
        item = cartItemService.createOrderDish(item);
        order.setCartItems(Set.of(item));
        order.setOrderTime(LocalDateTime.now());
        a = orderService.createOrder(order);


        order.setAddress("адрес2");
        order.setId(null);
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Самовывоз"));
        order.setDeliveryTime(LocalDateTime.now());
        //CartItem item = new CartItem(service.findDishById(1l), (byte) 4);
        item.setId(null);
        item = cartItemService.createOrderDish(item);
        order.setCartItems(Set.of(item));
        order.setOrderTime(LocalDateTime.now());
        a=orderService.createOrder(order);


        order.setAddress("адрес4");
        order.setId(null);
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Самовывоз"));
        order.setDeliveryTime(LocalDateTime.now());
        //CartItem item = new CartItem(service.findDishById(1l), (byte) 4);
        item.setId(null);
        item = cartItemService.createOrderDish(item);
        order.setCartItems(Set.of(item));
        order.setOrderTime(LocalDateTime.now());
        a=orderService.createOrder(order);

        order = new Order();
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер"));
        order.setDeliveryTime(LocalDateTime.now());
        CartItem item2 = new CartItem(service.findDishById(2L), (byte) 1);
        item2 = cartItemService.createOrderDish(item2);
        order.setCartItems(Set.of(item2));
        order.setOrderTime(LocalDateTime.now());
        a=orderService.createOrder(order);
        return 1;
    }
}
