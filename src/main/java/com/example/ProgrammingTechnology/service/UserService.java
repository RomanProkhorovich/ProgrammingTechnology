package com.example.ProgrammingTechnology.service;

import com.example.ProgrammingTechnology.model.Role;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.repository.RoleRepository;
import com.example.ProgrammingTechnology.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    public Long getIdByEmailOrPhone(String phoneOrEmail){
        return userRepository.findIdByPhoneOrEmail(phoneOrEmail).orElseThrow(()->new UsernameNotFoundException(""));
    }
    //TODO: сделать проверку почты при создании пользователя и при изменении почты
    //TODO: проверка по номеру телефона
    //создание пользователя
    public User createUser(User newUser) {
        if (userRepository.findByEmail(newUser.getEmail()).isEmpty()) {
            return userRepository.save(newUser);
        }
        throw new IllegalArgumentException();
    }

    //TODO: на проверку, проверку на номер телефона, проверку на email и на пароль
    public User createOrUpdate(User user) {
        //        Role role = roleRepository.findById(user.getRole().getId()).orElseThrow();
        if(user.getFirstname().isBlank() || user.getLastname().isBlank()) {
            throw new IllegalArgumentException();
        }
        if(user.getId()==null) {
            return createUser(user);
        }

        return updateUser(user);
    }
    
    //TODO: на проверку, те же вопроси что и в createOrUpdate
    public User updateUser(User upUser) {
        User user = findUserByEmailOrPhone(upUser.getEmail()==null?upUser.getPhone():upUser.getEmail());
        //User user = userRepository.findById(upUser.getId()).orElseThrow();
        //Role role = roleRepository.findById(upUser.getRole().getId()).orElseThrow();
        upUser.setRole(user.getRole());
        //upUser.setRole(roleService.findRoleById(upUser.getId()));
        if(upUser.getFirstname().isBlank()
                || upUser.getFirstname().isEmpty()
                || upUser.getLastname().isBlank()
                || upUser.getLastname().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return userRepository.save(upUser);
    }

    //поиск пользователя по id
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    //поиск пользователя по почте
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public User findUserByEmailOrPhone(String arg) {
        if (arg.contains("@"))
            return userRepository.findByEmail(arg).orElseThrow(() -> new UsernameNotFoundException(""));
        else
            return userRepository.findByPhone(arg).orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public User findUserByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow();
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
        if (!firstname.isEmpty() && !firstname.isBlank()) {
            user.setFirstname(firstname);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение фамилии пользователя
    public User updateLastname(Long id, String lastname) {
        User user = userRepository.findById(id).orElseThrow();
        if (!lastname.isEmpty() && !lastname.isBlank()) {
            user.setFirstname(lastname);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение отчества пользователя
    public User updateSurname(Long id, String surname) {
        User user = userRepository.findById(id).orElseThrow();
        if (!surname.isEmpty() && !surname.isBlank()) {
            user.setFirstname(surname);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение почты пользователя
    public User updateEmail(Long id, String email) {
        User user = userRepository.findById(id).orElseThrow();
        if (!email.isEmpty() && !email.isBlank()) {
            user.setFirstname(email);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    //изменение адреса пользователя
    public User updateAddress(Long id, String address) {
        User user = userRepository.findById(id).orElseThrow();
        if (!address.isEmpty() && !address.isBlank()) {
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
    public void deleteUserByEmail(String email) {
        var user = findUserByEmail(email);
        deleteUser(user);
    }
    public void deleteUserByPhone(String phone) {
        User user = findUserByPhone(phone);
        deleteUser(user);
    }

    //TODO: проверка на рабочий телефон
    public User updatePhone(Long id, String phone) {
        User user = userRepository.findById(id).orElseThrow();
        //проверочку бы
        user.setPhone(phone);
        userRepository.save(user);
        return user;
    }
}
