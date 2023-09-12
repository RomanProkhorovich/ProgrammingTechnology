package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Role;
import com.example.ProgrammingTechnology.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    //создание роли
    public Role createRole(Role newRole) {
        if(roleRepository.findById(newRole.getId()).isEmpty()) {
            return roleRepository.save(newRole);
        }
        throw new IllegalArgumentException();
    }

    //поиск роли по id
    public Role findRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    //поиск роли по названию
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow();
    }

    //поиск всех ролей
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }
}
