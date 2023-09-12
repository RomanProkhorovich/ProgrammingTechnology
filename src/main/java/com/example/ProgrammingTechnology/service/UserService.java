package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Role;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.repository.RoleRepository;
import com.example.ProgrammingTechnology.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    //TODO: сделать проверку почты при создании пользователя и при изменении почты

    //создание пользователя
    public User createUser(User newUser) {
        if(userRepository.findById(newUser.getId()).isEmpty()) {
            return userRepository.save(newUser);
        }
        throw new IllegalArgumentException();
    }

    //поиск пользователя по id
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    //поиск пользователя по почте
    public User findUserByEmail(String email) {
            return userRepository.findByEmail(email).orElseThrow();
    }

    //поиск пользователей по роли
    public List<User> findUsersByRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow();
        return userRepository.findAllByRole(role);
    }

    //поиск всех пользователей
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    //изменение имени пользователя
    public User updateFirstname(Long id, String firstname) {
        User user = userRepository.findById(id).orElseThrow();
        if(!firstname.isEmpty() && !firstname.isBlank()) {
            user.setFirstname(firstname);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение фамилии пользователя
    public User updateLastname(Long id, String lastname) {
        User user = userRepository.findById(id).orElseThrow();
        if(!lastname.isEmpty() && !lastname.isBlank()) {
            user.setFirstname(lastname);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение отчества пользователя
    public User updateSurname(Long id, String surname) {
        User user = userRepository.findById(id).orElseThrow();
        if(!surname.isEmpty() && !surname.isBlank()) {
            user.setFirstname(surname);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение почты пользователя
    public User updateEmail(Long id, String email) {
        User user = userRepository.findById(id).orElseThrow();
        if(!email.isEmpty() && !email.isBlank()) {
            user.setFirstname(email);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение адреса пользователя
    public User updateAddress(Long id, String address) {
        User user = userRepository.findById(id).orElseThrow();
        if(!address.isEmpty() && !address.isBlank()) {
            user.setFirstname(address);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение роли пользователя
    public User updateRole(Long id, Long roleId) {
        User user = userRepository.findById(id).orElseThrow();
        Role role = roleRepository.findById(roleId).orElseThrow();
        user.setRole(role);
        return userRepository.save(user);
    }

    //удаление пользователя по id
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    //удаление пользователя
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    //удаление пользователя по почте
    //TODO: помогите пожалуйста с методом
    public void deleteUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            //userRepository.delete(deleteUser(userRepository.findByEmail(email).orElseThrow()));
        }
    }
}
