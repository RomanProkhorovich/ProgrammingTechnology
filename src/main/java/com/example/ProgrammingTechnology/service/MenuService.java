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
            menuRepository.save(newMenu);
            return newMenu;
        }
        return null;
    }

    //поиск меню по id
    public Menu findMenuById(Long id) {
        if(menuRepository.findById(id).isPresent()) {
            return menuRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //поиск всех меню
    public List<Menu> findMenus() {
        return menuRepository.findAll();
    }

    //изменение меню
    public Menu updateMenu(Menu upMenu) {
        if(menuRepository.findById(upMenu.getId()).isPresent()) {
            menuRepository.findById(upMenu.getId()).ifPresent(menuRepository::delete);
            menuRepository.save(upMenu);
            return upMenu;
        }
        return null;
    }

    //изменение даты утверждения меню
    public Menu updateApproval(Long id, LocalDateTime approval) {
        if(menuRepository.findById(id).isPresent()) {
            Menu menu = menuRepository.findById(id).orElseThrow();
            menu.setApproval(approval);
            menuRepository.save(menu);
            return menu;
        }
        return null;
    }

    //удаление меню по id
    public void deleteMenuById(Long id) {
        if(menuRepository.findById(id).isPresent()) {
            menuRepository.deleteById(id);
        }
    }

    //удаление меню
    public void deleteMenu(Menu menu) {
        if(menuRepository.findById(menu.getId()).isPresent()) {
            menuRepository.delete(menu);
        }
    }

    //TODO: сделать добавление и удаления блюда из меню
}
