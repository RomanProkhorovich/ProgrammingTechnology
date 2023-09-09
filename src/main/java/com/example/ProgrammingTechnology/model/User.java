package com.example.ProgrammingTechnology.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "Customer")
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

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    public User(String firstname, String lastname, String surname, Role role, String email, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
