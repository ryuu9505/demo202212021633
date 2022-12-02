package com.example.demo6new.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class RoleHierarchy {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "role_name")
    private RoleHierarchy parent;

    @OneToMany(mappedBy = "parent")
    private Set<RoleHierarchy> children = new HashSet<>();
}
