package com.example.demo6new.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResourceRole {

    @Id
    @GeneratedValue
    @Column(name = "resource_role_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
