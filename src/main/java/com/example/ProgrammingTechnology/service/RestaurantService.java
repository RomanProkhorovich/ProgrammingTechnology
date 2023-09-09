package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.KitchenType;
import com.example.ProgrammingTechnology.model.Restaurant;
import com.example.ProgrammingTechnology.model.User;
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
        if(restaurantRepository.findById(newRestaurant.getId()).isEmpty()) {
            restaurantRepository.save(newRestaurant);
            return newRestaurant;
        }
        return null;
    }

    //поиск ресторана по id
    public Restaurant findRestaurantById(Long id) {
        if(restaurantRepository.findById(id).isPresent()) {
            return restaurantRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //поиск ресторанов по виду кухни
    public List<Restaurant> findRestaurantsByKitchenType(String name) {
        if(kitchenTypeService.findKitchenTypeByName(name).isPresent()) {
            KitchenType kitchenType = kitchenTypeService.findKitchenTypeByName(name).orElseThrow();
            return restaurantRepository.finaAllByKitchenType(kitchenType);
        }
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
        if(restaurantRepository.findById(id).isPresent() && !name.isEmpty() && !name.isBlank()) {
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            restaurant.setName(name);
            restaurantRepository.save(restaurant);
            return restaurant;
        }
        return null;
    }

    //изменение адреса ресторана
    public Restaurant updateAddress(Long id, String address) {
        if(restaurantRepository.findById(id).isPresent() && !address.isEmpty() && !address.isBlank()) {
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            restaurant.setAddress(address);
            restaurantRepository.save(restaurant);
            return restaurant;
        }
        return null;
    }

    //изменение видов кухни ресторана
    public Restaurant updateKitchenType(Long id, Set<KitchenType> kitchenTypeSet) {
        if(restaurantRepository.findById(id).isPresent() && !kitchenTypeSet.isEmpty()) {
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            restaurant.setKitchenTypes(kitchenTypeSet);
            restaurantRepository.save(restaurant);
            return restaurant;
        }
        return null;
    }

    //изменение менеджера ресторана
    public Restaurant updateManager(Long id, Long managerId) {
        if(restaurantRepository.findById(id).isPresent() && userService.findUserById(managerId)!=null) {
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            restaurant.setManager(userService.findUserById(managerId));
            restaurantRepository.save(restaurant);
            return restaurant;
        }
        return null;
    }

    //изменение количества мест в ресторане
    public Restaurant updatePeopleCount(Long id, Byte peopleCount) {
        if(restaurantRepository.findById(id).isPresent() && peopleCount>=0) {
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            restaurant.setPeopleCount(peopleCount);
            restaurantRepository.save(restaurant);
            return restaurant;
        }
        return null;
    }

    //изменение меню ресторана
    public Restaurant updateMenu(Long id, Long menuId) {
        if(restaurantRepository.findById(id).isPresent() && menuService.findMenuById(menuId)!=null) {
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            restaurant.setMenu(menuService.findMenuById(menuId));
            restaurantRepository.save(restaurant);
            return restaurant;
        }
        return null;
    }

    //удаление ресторана по id
    public void deleteRestaurantById(Long id) {
        if(restaurantRepository.findById(id).isPresent()) {
            restaurantRepository.deleteById(id);
        }
    }

    //удаление ресторана
    public void deleteRestaurant(Restaurant restaurant) {
        if(restaurantRepository.findById(restaurant.getId()).isPresent()) {
            restaurantRepository.delete(restaurant);
        }
    }
}
