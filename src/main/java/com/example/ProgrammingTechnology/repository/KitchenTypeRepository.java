package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.KitchenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KitchenTypeRepository extends JpaRepository<KitchenType, Long> {
    Optional<KitchenType> findByName(String name);
}