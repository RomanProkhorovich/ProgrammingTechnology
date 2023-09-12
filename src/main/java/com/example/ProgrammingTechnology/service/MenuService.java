package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Menu;
import com.example.ProgrammingTechnology.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    //создание меню
    public Menu createMenu(Menu newMenu) {
        if(menuRepository.findById(newMenu.getId()).isEmpty()) {
            return menuRepository.save(newMenu);
        }
        throw new IllegalArgumentException();
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
        Menu menu = menuRepository.findById(upMenu.getId()).orElseThrow();
        return menuRepository.save(upMenu);
    }

    //TODO: проверка новой даты, она не ожет быть позже настоящего времени
    //изменение даты утверждения меню
    public Menu updateApproval(Long id, LocalDateTime approval) {
        Menu menu = menuRepository.findById(id).orElseThrow();
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

    //TODO: сделать добавление и удаления блюда из меню
}
