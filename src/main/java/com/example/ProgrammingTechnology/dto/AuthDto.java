package com.example.ProgrammingTechnology.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthDto {

    //TODO: свериться с постановкой задачи по поводу авторизации и регистрации

    @JsonAlias("username")
    private String email;
    private String password;
}
