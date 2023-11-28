package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.AuthDto;
import com.example.ProgrammingTechnology.dto.RegistrationDto;
import com.example.ProgrammingTechnology.dto.UserDto;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.service.RoleService;
import com.example.ProgrammingTechnology.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final UserDetailsService service;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping
    public ResponseEntity<?> auth(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword());
        try {
            manager.authenticate(token);
            return ResponseEntity.ok(service.loadUserByUsername(authDto.getEmail()));

        }
        catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User doesnt exists");
        }
    }

    @GetMapping
    public ResponseEntity<?> r() {
        System.out.println("Get");
        return ResponseEntity.ok("");
    }

    @PostMapping("/reg")
    public ResponseEntity<?> registration(@RequestBody RegistrationDto reg) {
        User user = new User();
        user.setEmail(reg.getEmail());
        user.setRole(roleService.findRoleByName("USER"));
        user.setFirstname(reg.getFirstname());
        user.setLastname(reg.getLastname());
        user.setPassword(passwordEncoder.encode(reg.getPassword()));
        userService.createUser(user);
        return ResponseEntity.ok("");
    }
}
