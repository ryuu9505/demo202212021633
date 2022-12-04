package com.example.demo6new.domain;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = {"accounts", "resourcesSet"})
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @OrderBy("ordernum desc")
    private Set<Resource> resources = new LinkedHashSet<>();

}


