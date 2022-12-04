package com.example.demo6new.utility;

import com.example.demo6new.domain.Role;
import com.example.demo6new.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthorityMapper {

    private final RoleRepository roleRepository;

    public Set<Role> mapAuthorities(List<? extends GrantedAuthority> authorities) {
        Set<Role> mapped = new HashSet<>(authorities.size());
        for (GrantedAuthority authority : authorities) {
            mapped.add(roleRepository.findByName(authority.getAuthority())); // todo exception handling
        }
        return mapped;
    }
}
