package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.dto.RegistrationDto;
import com.example.ProgrammingTechnology.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final UserDetailsService service;


    @PostMapping()
    public ResponseEntity<?> auth(@RequestBody RegistrationDto regDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(regDto.getEmail(), regDto.getPassword());
        try {
            manager.authenticate(token);
            return ResponseEntity.ok(service.loadUserByUsername(regDto.getEmail()));

        }
        catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User doesnt exists");
        }
    }
}
