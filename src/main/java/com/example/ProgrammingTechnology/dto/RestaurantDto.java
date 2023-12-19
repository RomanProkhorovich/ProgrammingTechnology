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
    private Set<KitchenTypeDto> kitchenType = new LinkedHashSet<>();
    private UserDto manager;
    private Byte peopleCount;
    private MenuDto menu;
}
