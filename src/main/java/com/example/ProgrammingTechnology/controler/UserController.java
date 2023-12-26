package com.example.ProgrammingTechnology.controler;


import com.example.ProgrammingTechnology.dto.UserDto;
import com.example.ProgrammingTechnology.mapper.UserMapper;
import com.example.ProgrammingTechnology.model.Role;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.security.SecurityHelper;
import com.example.ProgrammingTechnology.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    @GetMapping()
    public UserDto findById(@RequestParam(name = "id",required = false) Long id) {
        if (id == null)
            id = service.findUserByEmailOrPhone(SecurityHelper.getCurrentUser().getUsername()).getId();

        return mapper.toDto(service.findUserById(id));
    }

    public UserDto changePhone(@PathParam("id") Long id, @PathParam("phone") String phone) {
        return mapper.toDto(service.updatePhone(id, phone));
    }

    public UserDto changeFirstname(@PathParam("id") Long id, @PathParam("firstname") String firstname) {
        return mapper.toDto(service.updateFirstname(id, firstname));
    }

    public UserDto changeLastname(@PathParam("id") Long id, @PathParam("lastname") String lastname) {
        return mapper.toDto(service.updateLastname(id, lastname));
    }

    public UserDto changeSurname(@PathParam("id") Long id, @PathParam("surname") String surname) {
        return mapper.toDto(service.updateSurname(id, surname));
    }

    public UserDto changeRole(@PathParam("id") Long id, @PathParam("role") Long roleId) {
        return mapper.toDto(service.updateRole(id, roleId));
    }

    public UserDto changeEmail(@PathParam("id") Long id, @PathParam("email") String email) {
        return mapper.toDto(service.updateEmail(id, email));
    }

    public UserDto changeAddress(@PathParam("id") Long id, @PathParam("address") String address) {
        return mapper.toDto(service.updateAddress(id, address));
    }

    //TODO: допилить вот это
    /*public UserDto changePassword(@PathParam("id") Long id, @PathParam("password") String password) {
        return mapper.toDto(service.updateP(id, surname));
    }*/

    //@GetMapping
    //public UserDto findById(@PathParam("email") String email) {
    //    return mapper.toDto(service.findUserByEmail(email));
    //}

    @GetMapping("/by_role")
    public List<UserDto> findAllByRole(@PathParam("role_id") Long roleId) {
        return mapper.toDtoList(service.findUsersByRole(roleId));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public List<UserDto> findAll() {
        if (SecurityHelper.getCurrentUserAuthority().equals("Admin"))
            return mapper.toDtoList(service.findUsers());
        else{
            var couriers = service.findUsersByRole(2l);
            var clients = service.findUsersByRole(1l);
            clients.addAll(couriers);
            return mapper.toDtoList(clients);
        }
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserDto user){
        User model = mapper.toModel(user);
        model.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(mapper.toDto(service.createOrUpdate(model)));
    }
}
