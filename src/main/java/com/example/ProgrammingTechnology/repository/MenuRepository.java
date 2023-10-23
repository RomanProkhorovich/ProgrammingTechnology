package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}