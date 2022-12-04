package com.example.demo6new.repository;

import com.example.demo6new.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    boolean existsByNameAndHttpMethod(String name, String httpMethod);
    Resource findByNameAndHttpMethod(String name, String httpMethod);
    @Query("select r from Resource r join fetch r.roles where r.type = 'url' order by r.orderNum desc")
    List<Resource> findAllResource();

}