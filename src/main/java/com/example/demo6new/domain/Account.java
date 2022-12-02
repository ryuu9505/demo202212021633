package com.example.demo6new.domain;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long accountId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoles = new ArrayList<>();

}
