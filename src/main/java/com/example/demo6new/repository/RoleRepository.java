package com.example.demo6new.repository;


import com.example.demo6new.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);
    Role findByName(String name);
}
