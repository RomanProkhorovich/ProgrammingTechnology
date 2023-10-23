package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.ReceivingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceivingTypeRepository extends JpaRepository<ReceivingType, Long> {
    Optional<ReceivingType> findByName(String name);
}
