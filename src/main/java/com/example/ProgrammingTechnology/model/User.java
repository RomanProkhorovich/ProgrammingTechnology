package com.example.ProgrammingTechnology.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "app_user")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(unique = true)
    private String email;

    public User(String firstname, String lastname, String surname, Role role, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.role = role;
        this.email = email;
    }
}
