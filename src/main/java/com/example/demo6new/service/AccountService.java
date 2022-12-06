package com.example.demo6new.service;

import com.example.demo6new.domain.Account;
import com.example.demo6new.domain.ProviderUser;
import com.example.demo6new.domain.form.AccountCreateForm;
import com.example.demo6new.domain.form.AccountModifyForm;
import com.example.demo6new.repository.AccountRepository;
import com.example.demo6new.utility.AuthorityMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public void modifyAccount(Long id, AccountModifyForm accountForm) { // todo EH
        // todo move to modifyAccountPassword() accountForm.setPassword(passwordEncoder.encode(accountForm.getPassword()));
        Account account = accountRepository.findById(id).get();
        modelMapper.map(accountForm, account);
        account.setRoles(roleService.getRolesByName(accountForm.getRoleNameList()));
        if (account.getRoles() == null) {
            account.setRoles(roleService.getDefaultRoles());
        }
        accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).get();
    } // todo EH

    public AccountModifyForm getAccountForm(Long id) {
        Account account = accountRepository.findById(id).get();
        AccountModifyForm accountModifyForm = modelMapper.map(account, AccountModifyForm.class);
        accountModifyForm.setRoleNameList(
                account.getRoles().stream().map(role ->
                        role.getName()).collect(Collectors.toList()));
        return accountModifyForm;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
