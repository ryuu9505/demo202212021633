package com.example.demo6new.repository;

import com.example.demo6new.domain.RoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    RoleHierarchy findByRoleName(String roleName);
}