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
    private Set<DishDto> dishDtos = new LinkedHashSet<>();

    public MenuDto(LocalDateTime date, Set<DishDto> dishDtos) {
        this.approval = date;
        this.dishDtos = dishDtos;
    }

    public MenuDto(LocalDateTime date) {
        this.approval = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuDto menuDto = (MenuDto) o;
        return Objects.equals(approval, menuDto.approval) && Objects.equals(dishDtos, menuDto.dishDtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(approval, dishDtos);
    }

}
