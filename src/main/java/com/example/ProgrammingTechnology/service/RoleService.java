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
            roleRepository.save(newRole);
            return newRole;
        }
        return null;
    }

    //поиск роли по id
    public Role findRoleById(Long id) {
        if(roleRepository.findById(id).isPresent()) {
            return roleRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //поиск роли по названию
    public Optional<Role> findRoleByName(String name) {
        if(roleRepository.findByName(name).isPresent()) {
            return roleRepository.findByName(name);
        }
        return null;
    }

    //поиск всех ролей
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }
}
