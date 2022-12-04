package com.example.demo5new.controller;

import com.example.demo5new.controller.form.AccountSaveForm;
import com.example.demo5new.service.AccountService;
import com.example.demo5new.validator.AccountSaveFormValidator;
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

    private final AccountSaveFormValidator accountSaveFormValidator;
    private final AccountService accountService;

    @InitBinder("accountSaveForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountSaveFormValidator);
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute(new AccountSaveForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpSubmit(@Validated AccountSaveForm accountSaveForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "signup";
        }
        accountService.saveAccount(accountSaveForm);
        return "redirect:/";
    }

}
