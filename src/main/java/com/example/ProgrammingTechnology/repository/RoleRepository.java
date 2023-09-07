package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
