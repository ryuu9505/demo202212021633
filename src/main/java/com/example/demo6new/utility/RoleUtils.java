package com.example.demo6new.utility;

import com.example.demo6new.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class RoleUtils {

    public static List<GrantedAuthority> mapRoles(Set<Role> roles) {
        List<GrantedAuthority> mapped = new ArrayList<>(roles.size());
        for (Role role : roles) {
            mapped.add(new SimpleGrantedAuthority(role.getName()));
        }
        return mapped;
    }

}
