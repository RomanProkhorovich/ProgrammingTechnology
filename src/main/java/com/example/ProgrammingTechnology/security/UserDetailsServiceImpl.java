package com.example.ProgrammingTechnology.security;

import com.example.ProgrammingTechnology.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.ProgrammingTechnology.model.User user;
        if (username.contains("@"))
            user = service.findUserByEmail(username);
        else user = service.findUserByPhone(username);

        return new User(username,
                user.getPassword(),
                Set.of(new SimpleGrantedAuthority(user.getRole().getName())));

    }
}
