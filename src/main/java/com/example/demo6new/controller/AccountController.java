package com.example.demo6new.controller;

import com.example.demo6new.domain.form.AccountCreateForm;
import com.example.demo6new.service.AccountService;
import com.example.demo6new.validator.AccountCreateFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountCreateFormValidator accountCreateFormValidator;
    private final AccountService accountService;

    @InitBinder("accountCreateForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountCreateFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new AccountCreateForm());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Validated AccountCreateForm form, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "sign-up";
        }
        accountService.createAccount(form);
        return "redirect:/";
    }

}
