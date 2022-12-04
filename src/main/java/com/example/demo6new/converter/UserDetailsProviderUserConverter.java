package com.example.demo6new.converter;

import com.example.demo6new.domain.Account;
import com.example.demo6new.domain.ProviderUser;
import com.example.demo6new.domain.form.ClientUser;

import static com.example.demo6new.utility.RoleUtils.mapRoles;

public class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if(providerUserRequest.account() == null){
            return null;
        }

        Account account = providerUserRequest.account();
        return ClientUser.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(mapRoles(account.getRoles()))
                .email(account.getEmail())
                .build();
    }



}
