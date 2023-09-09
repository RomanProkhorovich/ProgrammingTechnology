package com.example.ProgrammingTechnology.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReceivingType {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
}
