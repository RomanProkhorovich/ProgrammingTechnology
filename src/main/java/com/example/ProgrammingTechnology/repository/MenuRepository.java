package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByActual(boolean actual);
}