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
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

    //поиск пользователя по id
    public User findUserById(Long id) {
        if(userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).orElseThrow();
        }
        return null;
    }

    //поиск пользователя по почте
    public Optional<User> findUserByEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            return userRepository.findByEmail(email);
        }
        return null;
    }

    //поиск пользователей по роли
    public List<User> findUsersByRole(Long id) {
        if(roleRepository.findById(id).isPresent()) {
            Role role = roleRepository.findById(id).orElseThrow();
            return userRepository.findAllByRole(role);
        }
        return null;
    }

    //поиск всех пользователей
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    //изменение имени пользователя
    public User updateFirstname(Long id, String firstname) {
        if(!firstname.isEmpty() && !firstname.isBlank() && userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).orElseThrow();
            user.setFirstname(firstname);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    //изменение фамилии пользователя
    public User updateLastname(Long id, String lastname) {
        if(!lastname.isEmpty() && !lastname.isBlank() && userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).orElseThrow();
            user.setFirstname(lastname);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    //изменение отчества пользователя
    public User updateSurname(Long id, String surname) {
        if(!surname.isEmpty() && !surname.isBlank() && userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).orElseThrow();
            user.setFirstname(surname);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    //изменение почты пользователя
    public User updateEmail(Long id, String email) {
        if(!email.isEmpty() && !email.isBlank() && userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).orElseThrow();
            user.setFirstname(email);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    //изменение адреса пользователя
    public User updateAddress(Long id, String address) {
        if(!address.isEmpty() && !address.isBlank() && userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).orElseThrow();
            user.setFirstname(address);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    //изменение роли пользователя
    public User updateRole(Long id, Long roleId) {
        if(roleRepository.findById(roleId).isPresent() && userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).orElseThrow();
            Role role = roleRepository.findById(roleId).orElseThrow();
            user.setRole(role);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    //удаление пользователя по id
    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    //удаление пользователя
    public void deleteUser(User user) {
        if(userRepository.findById(user.getId()).isPresent()) {
            userRepository.delete(user);
        }
    }

    //удаление пользователя по почте
    //TODO: помогите пожалуйста с методом
    public void deleteUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            //userRepository.delete(deleteUser(userRepository.findByEmail(email).orElseThrow()));
        }
    }
}
