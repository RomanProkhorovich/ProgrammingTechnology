package com.example.ProgrammingTechnology.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MenuDto {
    private Long id;
    private LocalDateTime approval;
    private Set<DishDto> dishes = new LinkedHashSet<>();
}
