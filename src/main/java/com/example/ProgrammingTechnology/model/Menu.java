package com.example.ProgrammingTechnology.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime approval;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "Menu_Dish",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private Set<Dish> dishes = new LinkedHashSet<>();

    public Menu(LocalDateTime date, Set<Dish> dishes) {
        this.approval = date;
        this.dishes = dishes;
    }

    public Menu(LocalDateTime date) {
        this.approval = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(approval, menu.approval) && Objects.equals(dishes, menu.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(approval, dishes);
    }

}
