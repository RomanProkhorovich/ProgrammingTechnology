package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Dish;
import com.example.ProgrammingTechnology.model.Menu;
import com.example.ProgrammingTechnology.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    //создание меню
    public Menu createMenu(Menu newMenu) {
        if (newMenu.getId() != null && menuRepository.findById(newMenu.getId()).isPresent()) {
            throw new IllegalArgumentException();
        }
        var actual = getActual();
        if (actual != null) {
            actual.setActual(false);
            menuRepository.save(actual);
            newMenu.setActual(true);
        }
        return menuRepository.save(newMenu);
    }

    public Menu getActual() {
        List<Menu> allByActual = menuRepository.findAllByActual(true);
        if (allByActual.isEmpty())
            return null;
        return allByActual.get(0);
    }

    //поиск меню по id
    public Menu findMenuById(Long id) {
        return menuRepository.findById(id).orElseThrow();
    }

    //поиск всех меню
    public List<Menu> findMenus() {
        return menuRepository.findAll();
    }

    //изменение меню
    public Menu updateMenu(Menu upMenu) {
        //TODO: fix
        Menu menu = menuRepository.findById(upMenu.getId()).orElseThrow();
        return menuRepository.save(upMenu);
    }

    //изменение даты утверждения меню
    public Menu updateApproval(Long id, LocalDateTime approval) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        if (approval.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException();
        menu.setApproval(approval);
        return menuRepository.save(menu);
    }

    //удаление меню по id
    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }

    //удаление меню
    public void deleteMenu(Menu menu) {
        menuRepository.delete(menu);
    }

    public Menu addDish(Long menuId, Dish dish) {
        var menu = findMenuById(menuId);
        var dishes = new HashSet<>(menu.getDishes());
        dishes.add(dish);
        menu.setDishes(dishes);
        return menuRepository.save(menu);
    }

    public Menu deleteDish(Long menuId, Long dishId) {
        var menu = findMenuById(menuId);
        var dishes = new HashSet<>(menu.getDishes());
        var newDishes = dishes.stream().filter(x -> !x.getId().equals(dishId)).collect(Collectors.toSet());
        menu.setDishes(newDishes);
        return menuRepository.save(menu);
    }
}
