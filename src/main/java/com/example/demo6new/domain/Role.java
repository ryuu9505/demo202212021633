package com.example.demo6new.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Account> accounts = new ArrayList<>();

    @ManyToMany(mappedBy = "roles")
    private List<Resource> resources = new ArrayList<>();

}


