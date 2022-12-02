package com.example.demo6new.service;

import com.example.demo6new.domain.Account;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.AccountSaveForm;
import com.example.demo6new.domain.form.ClientUser;
import com.example.demo6new.repository.AccountRepository;
import com.example.demo6new.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    private final String PREFIX_ROLE = "ROLE_";
    private final String PREFIX_SCOPE = "SCOPE_";

    public void saveAccount(AccountSaveForm form) {
        if (form.getRoles() == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("ROLE_USER")); // todo exception handling
            form.setRoles(roles);
        }
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        Account account = modelMapper.map(form, Account.class);

        accountRepository.save(account);
    }

    public void modifyAccount(AccountSaveForm form) {
        if (form.getRoles() == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("ROLE_USER")); // todo exception handling
            form.setRoles(roles);
        }
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        Account account = modelMapper.map(form, Account.class);
        accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElse(new Account());
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
