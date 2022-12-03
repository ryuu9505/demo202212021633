package com.example.demo6new.service;

import com.example.demo6new.converter.ProviderUserConverter;
import com.example.demo6new.converter.ProviderUserRequest;
import com.example.demo6new.domain.Account;
import com.example.demo6new.domain.PrincipalUser;
import com.example.demo6new.domain.ProviderUser;
import com.example.demo6new.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService extends AbstractOAuth2UserService implements UserDetailsService {

    private AccountRepository accountRepository;

    public CustomUserDetailsService(AccountService accountService, AccountRepository accountRepository, ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter) {
        super(accountService, accountRepository, providerUserConverter);
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException: Username=" + username);
        }

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(account);
        ProviderUser providerUser = getProviderUser(providerUserRequest);
        return new PrincipalUser(providerUser);

    }

}