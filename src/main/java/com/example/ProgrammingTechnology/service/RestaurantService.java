package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.exception.AttributeException;
import com.example.ProgrammingTechnology.model.KitchenType;
import com.example.ProgrammingTechnology.model.Restaurant;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final KitchenTypeService kitchenTypeService;
    private final UserService userService;
    private final MenuService menuService;

    //создание ресторана
    public Restaurant createRestaurant(Restaurant newRestaurant) {
        if (!Objects.equals(newRestaurant.getManager().getRole().getName(), "Manager"))
            throw new AttributeException("Челик не манагер", "manager", null);
        if (newRestaurant.getId() == null || restaurantRepository.findById(newRestaurant.getId()).isEmpty()) {
            return restaurantRepository.save(newRestaurant);
        }

        throw new IllegalArgumentException();
    }

    //поиск ресторана по id
    public Restaurant findRestaurantById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("id cant be null");
        return restaurantRepository.findById(id).orElseThrow();
    }

    public List<Restaurant> saveAll(List<Restaurant> list) {
        return list.stream().map(restaurantRepository::save).collect(Collectors.toList());
    }

    //поиск ресторанов по виду кухни
    public List<Restaurant> findRestaurantsByKitchenType(String name) {
        KitchenType kitchenType = kitchenTypeService.findKitchenTypeByName(name);
        return restaurantRepository.findAllByKitchenTypesContains(kitchenType);
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
        throw new AttributeException("","name",id);
    }

    //изменение адреса ресторана
    public Restaurant updateAddress(Long id, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        if (!address.isEmpty() && !address.isBlank()) {
            restaurant.setAddress(address);
            return restaurantRepository.save(restaurant);
        }
        throw new AttributeException("","address",id);
    }

    //изменение видов кухни ресторана
    public Restaurant updateKitchenType(Long id, Set<KitchenType> kitchenTypeSet) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        if (!kitchenTypeSet.isEmpty()) {
            restaurant.setKitchenTypes(kitchenTypeSet);
            return restaurantRepository.save(restaurant);
        }
        throw new AttributeException("","kitchenType",id);
    }

    //изменение менеджера ресторана
    public Restaurant updateManager(Long id, Long managerId) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->
         new AttributeException("","id",id));
        User userById = userService.findUserById(managerId);
        if (!Objects.equals(userById.getRole().getName(), "Manager"))
            throw new AttributeException("","manager",id);
        restaurant.setManager(userById);
        return restaurantRepository.save(restaurant);
    }

    //изменение количества мест в ресторане
    public Restaurant updatePeopleCount(Long id, Byte peopleCount) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        if (peopleCount >= 0) {
            restaurant.setPeopleCount(peopleCount);
            return restaurantRepository.save(restaurant);
        }
        throw new AttributeException("","peopleCount",id);
    }

    //изменение меню ресторана
    public Restaurant updateMenu(Long id, Long menuId) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->
                new AttributeException("","id",id));
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
