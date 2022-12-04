package com.example.demo6new.repository;

import com.example.demo6new.domain.AccessIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {
    boolean existsByAddress(String address);
}
