package com.example.ProgrammingTechnology.repository;

import com.example.ProgrammingTechnology.model.Role;
import com.example.ProgrammingTechnology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    @Query("SELECT id FROM User c where c.phone = ?1 or c.email = ?1 ")
    Optional<Long> findIdByPhoneOrEmail(String phoneOrEmail);
    List<User> findAllByRole(Role role);
}
