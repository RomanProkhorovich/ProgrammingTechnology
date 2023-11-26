package com.example.ProgrammingTechnology.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
    private Set<KitchenTypeDto> kitchenTypeDtos = new LinkedHashSet<>();
    private UserDto manager;
    private Byte peopleCount;
    private MenuDto menuDto;

    public RestaurantDto(String name, String address, Set<KitchenTypeDto> kitchenTypeDtos, UserDto user, Byte peopleCount) {
        this.name = name;
        this.address = address;
        this.kitchenTypeDtos = kitchenTypeDtos;
        this.manager = user;
        this.peopleCount = peopleCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDto that = (RestaurantDto) o;
        return Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(kitchenTypeDtos, that.kitchenTypeDtos) && Objects.equals(manager, that.manager) && Objects.equals(peopleCount, that.peopleCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, kitchenTypeDtos, manager, peopleCount);
    }
}
