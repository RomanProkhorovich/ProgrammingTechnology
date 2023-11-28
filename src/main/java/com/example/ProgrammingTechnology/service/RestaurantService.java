package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.KitchenType;
import com.example.ProgrammingTechnology.model.Restaurant;
import com.example.ProgrammingTechnology.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final KitchenTypeService kitchenTypeService;
    private final UserService userService;
    private final MenuService menuService;

    //создание ресторана
    public Restaurant createRestaurant(Restaurant newRestaurant) {
        if (restaurantRepository.findById(newRestaurant.getId()).isEmpty()) {
            return restaurantRepository.save(newRestaurant);
        }
        throw new IllegalArgumentException();
    }

    //поиск ресторана по id
    public Restaurant findRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElseThrow();
    }

    //поиск ресторанов по виду кухни
    public List<Restaurant> findRestaurantsByKitchenType(String name) {
        KitchenType kitchenType = kitchenTypeService.findKitchenTypeByName(name);
        return null;
    }

    //поиск ресторанов по названию
    public List<Restaurant> findRestaurantsByName(String name) {
        return restaurantRepository.findAllByName(name);
    }

    //поиск всех ресторанов
    public List<Restaurant> findRestaurants() {
        return restaurantRepository.findAll();
    }

    //изменение названия ресторана
    public Restaurant updateName(Long id, String name) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        if (!name.isEmpty() && !name.isBlank()) {
            restaurant.setName(name);
            return restaurantRepository.save(restaurant);
        }
        throw new IllegalArgumentException();
    }

    //изменение адреса ресторана
    public Restaurant updateAddress(Long id, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        if (!address.isEmpty() && !address.isBlank()) {
            restaurant.setAddress(address);
            return restaurantRepository.save(restaurant);
        }
        throw new IllegalArgumentException();
    }

    //изменение видов кухни ресторана
    public Restaurant updateKitchenType(Long id, Set<KitchenType> kitchenTypeSet) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        if (!kitchenTypeSet.isEmpty()) {
            restaurant.setKitchenTypes(kitchenTypeSet);
            return restaurantRepository.save(restaurant);
        }
        throw new IllegalArgumentException();
    }

    //изменение менеджера ресторана
    public Restaurant updateManager(Long id, Long managerId) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setManager(userService.findUserById(managerId));
        return restaurantRepository.save(restaurant);
    }

    //изменение количества мест в ресторане
    public Restaurant updatePeopleCount(Long id, Byte peopleCount) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        if (peopleCount >= 0) {
            restaurant.setPeopleCount(peopleCount);
            return restaurantRepository.save(restaurant);
        }
        throw new IllegalArgumentException();
    }

    //изменение меню ресторана
    public Restaurant updateMenu(Long id, Long menuId) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setMenu(menuService.findMenuById(menuId));
        return restaurantRepository.save(restaurant);
    }

    //удаление ресторана по id
    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }

    //удаление ресторана
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }
}
