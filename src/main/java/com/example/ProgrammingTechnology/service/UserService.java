package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
}
