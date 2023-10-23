package com.example.ProgrammingTechnology.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String surname;
    private RoleDto roleDto;
    private String email;
    private String address;
    private String password;

    public UserDto(String firstname, String lastname, String surname, RoleDto roleDto, String email, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.roleDto = roleDto;
        this.email = email;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto user = (UserDto) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
