package com.example.demo6new.controller;

import com.example.demo6new.domain.form.AccountCreateForm;
import com.example.demo6new.repository.AccountRepository;
import com.example.demo6new.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    final String USERNAME = "user679435";
    final String PASSWORD = "12345678";
    final String WRONG_USERNAME = "userxxxxx";
    final String WRONG_PASSWORD = "87654321";

    @BeforeEach
    void beforeEach() {
        AccountCreateForm accountCreateForm = new AccountCreateForm();
        accountCreateForm.setUsername(USERNAME);
        accountCreateForm.setPassword(PASSWORD);
        accountService.createAccount(accountCreateForm);
    }

    @AfterEach
    void afterEach() {
        accountRepository.deleteAll();
    }


    @DisplayName("Login")
    @Test
    void login() throws Exception {
        mockMvc.perform(post("/login-proc")
                        .param("username", USERNAME)
                        .param("password", PASSWORD)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername(USERNAME));
    }

    @DisplayName("Login with wrong username")
    @Test
    void login_with_wrong_username() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", WRONG_USERNAME)
                        .param("password", PASSWORD)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(unauthenticated());
    }

    @DisplayName("Login with wrong password")
    @Test
    void login_with_wrong_password() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", USERNAME)
                        .param("password", WRONG_PASSWORD)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(unauthenticated());
    }

    @WithUserDetails(value=USERNAME, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("Logout")
    @Test
    void logout() throws Exception {
        mockMvc.perform(post("/logout")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?logout"))
                .andExpect(unauthenticated());
    }

}