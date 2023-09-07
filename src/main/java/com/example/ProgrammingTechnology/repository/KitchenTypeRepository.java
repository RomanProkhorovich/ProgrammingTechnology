package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.KitchenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenTypeRepository extends JpaRepository<KitchenType, Long> {
}