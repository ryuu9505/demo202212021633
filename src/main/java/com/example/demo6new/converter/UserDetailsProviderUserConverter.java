package com.example.demo5new.common.converters;

import com.example.demo5new.common.authority.RoleMapper;
import com.example.demo5new.domain.Role;
import com.example.demo5new.domain.users.Account;
import com.example.demo5new.domain.users.ProviderUser;
import com.example.demo5new.domain.users.form.FormUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest,ProviderUser> {

    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if(providerUserRequest.account() == null){
            return null;
        }

        Account account = providerUserRequest.account();
        return FormUser.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(mapRoles(account.getUserRoles()))
                .email(account.getEmail())
                .provider("none")
                .build();
    }

    public List<GrantedAuthority> mapRoles(Set<Role> roles) {
        List<GrantedAuthority> mapped = new ArrayList<>(roles.size());
        for (Role role : roles) {
            mapped.add(mapRole(role.getRoleName()));
        }
        return mapped;
    }

    private GrantedAuthority mapRole(String name) {
        return new SimpleGrantedAuthority(name);
    }

}
