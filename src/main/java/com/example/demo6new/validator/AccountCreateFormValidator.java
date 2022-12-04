package com.example.demo6new.validator;

import com.example.demo6new.domain.form.AccountCreateForm;
import com.example.demo6new.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountCreateFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountCreateForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountCreateForm accountSaveForm = (AccountCreateForm) target;
        if (accountRepository.existsByEmail(accountSaveForm.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{accountSaveForm.getEmail()}, "Invalid Email");
        }
        if (accountRepository.existsByUsername(accountSaveForm.getUsername())) {
            errors.rejectValue("username", "invalid.username", new Object[]{accountSaveForm.getUsername()}, "Invalid Username");
        }
    }
}