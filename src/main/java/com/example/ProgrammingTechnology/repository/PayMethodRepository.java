package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod,Long> {
    Optional<PayMethod> findByName(String name);
}
