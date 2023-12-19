package com.example.ProgrammingTechnology.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationDto {
    private String firstname;
    private String lastname;
    private String surname;
    private RoleDto role;
    private String email;
    private String password;
}
