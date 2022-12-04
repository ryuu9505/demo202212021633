package com.example.demo6new.service;

import com.example.demo6new.domain.Account;
import com.example.demo6new.domain.ProviderUser;
import com.example.demo6new.domain.Role;
import com.example.demo6new.domain.form.AccountCreateForm;
import com.example.demo6new.domain.form.AccountForm;
import com.example.demo6new.domain.form.AccountModifyForm;
import com.example.demo6new.repository.AccountRepository;
import com.example.demo6new.repository.RoleRepository;
import com.example.demo6new.utility.AuthorityMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final ModelMapper modelMapper;
    private final AuthorityMapper authorityMapper;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RoleService roleService;

    private final String PREFIX_ROLE = "ROLE_";
    private final String PREFIX_SCOPE = "SCOPE_";

    public void createAccount(AccountCreateForm form) {
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        Account account = modelMapper.map(form, Account.class);
        account.setRoles(roleService.getDefaultRoles());
        accountRepository.save(account);
    }

    public void createAccount(ProviderUser providerUser) {

        Account account = Account.builder()
                .email(providerUser.getEmail())
                .provider(providerUser.getProvider())
                .roles(authorityMapper.mapAuthorities(providerUser.getAuthorities()))
                .username(providerUser.getUsername())
                .nickname(providerUser.getNickname())
                .picture(providerUser.getPicture())
                .build();

        accountRepository.save(account);
    }

    public void modifyAccount(Long id, AccountModifyForm form) {
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        Account account = accountRepository.findById(id).orElseThrow();
        modelMapper.map(form, account);
        if (account.getRoles() == null) {
            account.setRoles(roleService.getDefaultRoles());
        }
        accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow();
    } // todo EH

    public AccountForm getAccountForm(Long id) {
        return modelMapper.map(accountRepository.findById(id), AccountForm.class);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
