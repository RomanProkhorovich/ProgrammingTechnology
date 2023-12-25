package com.example.ProgrammingTechnology.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityHelper {

    public static UserDetails getCurrentUser(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        return (UserDetails)authentication.getPrincipal();
    }
    public static String getCurrentUserAuthority(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("");
    }
}
