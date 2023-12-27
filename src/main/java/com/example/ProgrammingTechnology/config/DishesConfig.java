package com.example.ProgrammingTechnology.config;

import com.example.ProgrammingTechnology.model.*;
import com.example.ProgrammingTechnology.repository.PayMethodRepository;
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
    private final OrderStatusService orderStatusService;

    @Bean
    int addData() throws IOException {

        return 1;
    }

    @Bean
    int addUsers() throws IOException {
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

        User manager2 = User.builder()
                .phone("89271049262")
                .firstname("Арина")
                .surname("Николаевна")
                .lastname("Пономарева")
                .role(roleService.findRoleByName("Manager"))
                .email("arina.pon@example.ru")
                .password(passwordEncoder.encode("ponarin"))
                .build();
        userService.createOrUpdate(manager2);


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
        fi = new File("src//main//resources//еда//оливье.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        olive.setPhoto(fileContent);
        service.createDish(olive);

        Dish grib = new Dish();
        grib.setPrice(160f);
        grib.setDescription("Вкуснейший грибной крем-суп из шампиньонов и картофеля, с нежной текстурой. Идеально подходит для диетического питания");
        grib.setName("Грибной суп");
        grib.setWeight(250f);
        grib.setCalories(200f);
        fi = new File("src//main//resources//еда//грибной_суп.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        grib.setPhoto(fileContent);
        service.createDish(grib);

        Dish skumb = new Dish();
        skumb.setPrice(310f);
        skumb.setDescription("Скумбрия на гриле представляет собой восхитительное и питательное блюдо, идеально подходящее для тех, кто предпочитает здоровую пищу. Этот деликатес приготовлен из свежей, высококачественной скумбрии, которая тщательно очищена от костей и кожи, а затем замаринована в смеси ароматных специй и трав. Подается с пастой казаречче");
        skumb.setName("Скумбрия на гриле");
        skumb.setWeight(290f);
        skumb.setCalories(500f);
        fi = new File("src//main//resources//еда//скумбрия_на_гриле.jpeg");
        fileContent = Files.readAllBytes(fi.toPath());
        skumb.setPhoto(fileContent);
        service.createDish(skumb);

        Dish sir = new Dish();
        sir.setPrice(190f);
        sir.setDescription("С сухариками");
        sir.setName("Сырный суп");
        sir.setWeight(245f);
        sir.setCalories(150f);
        fi = new File("src//main//resources//еда//сырный_суп.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        sir.setPhoto(fileContent);
        service.createDish(sir);

        Dish sirniki = new Dish();
        sirniki.setPrice(330f);
        sirniki.setDescription("Со сметаной и клубничным вареньем");
        sirniki.setName("Сырники");
        sirniki.setWeight(185f);
        sirniki.setCalories(400f);
        fi = new File("src//main//resources//еда//сырники.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        sirniki.setPhoto(fileContent);
        service.createDish(sirniki);

        Dish brus = new Dish();
        brus.setPrice(600f);
        brus.setDescription("С креветками");
        brus.setName("Брускетты");
        brus.setWeight(200f);
        brus.setCalories(200f);
        fi = new File("src//main//resources//еда//брускетты.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        brus.setPhoto(fileContent);
        service.createDish(brus);

        Dish cezar = new Dish();
        cezar.setPrice(480f);
        cezar.setDescription("В его основе - свежие листья салата романо, которые придают салату свежесть и легкость. Гренки из белого хлеба, пропитанные чесночным маслом, добавляют в салат хрустящую текстуру и приятный аромат. Нежнейшее куриное филе, обжаренное до золотистой корочки, делает салат сытным и питательным. А изюминкой салата является соус “Цезарь”, приготовленный по особому рецепту с добавлением анчоусов и вустерширского соуса. Все ингредиенты аккуратно перемешиваются и выкладываются на тарелку, а сверху посыпаются тертым сыром пармезан. Салат “Цезарь” выглядит очень красиво и аппетитно");
        cezar.setName("Цезарь");
        cezar.setWeight(230f);
        cezar.setCalories(310f);
        fi = new File("src//main//resources//еда//цезарь.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        cezar.setPhoto(fileContent);
        service.createDish(cezar);

        Dish borsch = new Dish();
        borsch.setPrice(295f);
        borsch.setDescription("Со сметаной");
        borsch.setName("Борщ");
        borsch.setWeight(330f);
        borsch.setCalories(230f);
        fi = new File("src//main//resources//еда//борщ.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        borsch.setPhoto(fileContent);
        service.createDish(borsch);

        Dish lazan = new Dish();
        lazan.setPrice(340f);
        lazan.setDescription("Мясная");
        lazan.setName("Лазанья");
        lazan.setWeight(220f);
        lazan.setCalories(500f);
        fi = new File("src//main//resources//еда//лазанья.jpg");
        fileContent = Files.readAllBytes(fi.toPath());
        lazan.setPhoto(fileContent);
        service.createDish(lazan);

        Dish palki = new Dish();
        palki.setPrice(380f);
        palki.setDescription("С фирменным соусом");
        palki.setName("Сырные палочки");
        palki.setWeight(260f);
        palki.setCalories(600f);
        fi = new File("src//main//resources//еда//сырные_палочки.jpeg");
        fileContent = Files.readAllBytes(fi.toPath());
        palki.setPhoto(fileContent);
        service.createDish(palki);



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

        Restaurant rest1 = new Restaurant();
        rest1.setName("Roman's на Калужской");
        rest1.setAddress("г. Самара, ул. Калужская, 11");
        rest1.setPeopleCount((byte) 60);
        rest1.setMenu(menu);
        rest1.setManager(userService.findUserByEmail("kyza.misha@example.ru"));
        restaurantService.createRestaurant(rest1);

        Restaurant rest2 = new Restaurant();
        rest2.setName("Roman's на Мичурина");
        rest2.setAddress("г. Самара, ул. Мичурина, 148");
        rest2.setPeopleCount((byte) 33);
        rest2.setMenu(menu);
        rest2.setManager(userService.findUserByEmail("arina.pon@example.ru"));
        restaurantService.createRestaurant(rest2);
       /* user.setId(null);
        userService.createUser(user);*/

        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Самовывоз"));
        CartItem item = new CartItem(service.findDishById(1l), (byte) 4);
        item = cartItemService.createOrderDish(item);
        order.setCartItems(Set.of(item));
        order.setOrderTime(LocalDateTime.now());
        var a = orderService.createOrder(order);

        order.setAddress("адрес1");
        order.setId(null);
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Самовывоз"));
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
        CartItem item2 = new CartItem(service.findDishById(2L), (byte) 1);
        item2 = cartItemService.createOrderDish(item2);
        order.setCartItems(Set.of(item2));
        order.setOrderTime(LocalDateTime.now());
        a=orderService.createOrder(order);

        order = new Order();
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setRestaurant(rest1);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер"));
        CartItem item3 = new CartItem(service.findDishById(1L), (byte) 1);
        item3 = cartItemService.createOrderDish(item3);
        order.setCartItems(Set.of(item3));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(orderStatusService.findOrderStatusByName("В обработке"));
        order.setCourier(cur);
        a=orderService.createOrder(order);

        order = new Order();

        order.setRestaurant(rest1);
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер"));
        CartItem item4 = new CartItem(service.findDishById(3L), (byte) 1);
        item4 = cartItemService.createOrderDish(item4);
        order.setCartItems(Set.of(item4));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(orderStatusService.findOrderStatusByName("Принят"));
        order.setCourier(cur);
        a=orderService.createOrder(order);

        order = new Order();

        order.setRestaurant(rest1);
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер"));
        CartItem item5 = new CartItem(service.findDishById(2L), (byte) 3);
        item5 = cartItemService.createOrderDish(item5);
        order.setCartItems(Set.of(item5));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(orderStatusService.findOrderStatusByName("Готовится"));
        order.setCourier(cur);
        a=orderService.createOrder(order);

        order = new Order();

        order.setRestaurant(rest1);
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер"));
        CartItem item6 = new CartItem(service.findDishById(2L), (byte) 3);
        item6 = cartItemService.createOrderDish(item6);
        order.setCartItems(Set.of(item6));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(orderStatusService.findOrderStatusByName("Передан курьеру"));
        order.setCourier(cur);
        a=orderService.createOrder(order);

        order = new Order();

        order.setRestaurant(rest2);
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер"));
        CartItem item7 = new CartItem(service.findDishById(1L), (byte) 5);
        item7 = cartItemService.createOrderDish(item7);
        order.setCartItems(Set.of(item7));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(orderStatusService.findOrderStatusByName("Готов"));
        order.setCourier(cur);
        a=orderService.createOrder(order);

        order = new Order();

        order.setRestaurant(rest1);
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер"));
        CartItem item8 = new CartItem(service.findDishById(4L), (byte) 1);
        item8 = cartItemService.createOrderDish(item8);
        order.setCartItems(Set.of(item8));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(orderStatusService.findOrderStatusByName("Доставлен"));
        order.setCourier(cur);
        a=orderService.createOrder(order);

        order = new Order();

        order.setRestaurant(rest1);
        order.setAddress(user.getAddress());
        order.setClient(user);
        order.setReceivingType(receivingTypeService.findReceivingTypeByName("Курьер"));
        CartItem item9 = new CartItem(service.findDishById(5L), (byte) 3);
        item9 = cartItemService.createOrderDish(item9);
        order.setCartItems(Set.of(item9));
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(orderStatusService.findOrderStatusByName("Завершен"));
        order.setCourier(cur);
        a=orderService.createOrder(order);

        return 1;
    }
}
