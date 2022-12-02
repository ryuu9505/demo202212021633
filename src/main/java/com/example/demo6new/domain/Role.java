package com.example.demo6new.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<AccountRole> accountRoles = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<ResourceRole> resourceRoles = new ArrayList<>();

}


