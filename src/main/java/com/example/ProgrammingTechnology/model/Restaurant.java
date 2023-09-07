package com.example.ProgrammingTechnology.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name","address"}))
public class Restaurant {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Set<KitchenType> kitchenTypes = new LinkedHashSet<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private User manager;

    @Column(nullable = false)
    private Byte peopleCount;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Restaurant(String name, String address, Set<KitchenType> kitchenTypes, User user, Byte peopleCount) {
        this.name = name;
        this.address = address;
        this.kitchenTypes = kitchenTypes;
        this.manager = user;
        this.peopleCount = peopleCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(kitchenTypes, that.kitchenTypes) && Objects.equals(manager, that.manager) && Objects.equals(peopleCount, that.peopleCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, kitchenTypes, manager, peopleCount);
    }
}
