package com.example.ProgrammingTechnology.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String surname;
    private RoleDto role;
    private String email;
    private String address;
    private String password;

}
