package com.example.ProgrammingTechnology.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDto {
    private List<IdAndCount> dishes;
    private String address;
    private Long restaurantId;
    private String receivingTypeDto;
    private Long clientId;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class IdAndCount{
        private Long id;
        private byte count;
    }
}
