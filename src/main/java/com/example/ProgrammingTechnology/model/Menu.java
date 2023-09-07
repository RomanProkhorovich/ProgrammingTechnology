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
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime approvalDate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "Menu_dishes",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dishes_id"))
    private Set<Dish> dishes = new LinkedHashSet<>();

    public Menu(LocalDateTime date, Set<Dish> dishes) {
        this.approvalDate = date;
        this.dishes = dishes;
    }

    public Menu(LocalDateTime date) {
        this.approvalDate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(approvalDate, menu.approvalDate) && Objects.equals(dishes, menu.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(approvalDate, dishes);
    }

}
