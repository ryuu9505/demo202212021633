package com.example.demo6new.controller;

import com.example.demo6new.domain.Account;
import com.example.demo6new.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    final String VALID_USERNAME = "user75";
    final String VALID_EMAIL = "user75@email.com";
    final String VALID_PASSWORD = "password";
    final String INVALID_USERNAME = "us";
    final String INVALID_EMAIL = "email";
    final String INVALID_PASSWORD = "passwor";

    @DisplayName("Sign Up Page")
    @Test
    void signUpForm() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"));
    }

    @DisplayName("Sign Up with invalid input")
    @Test
    void signUpSubmit_with_invalid_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                        .param("username", INVALID_USERNAME)
                        .param("email", VALID_EMAIL)
                        .param("password", VALID_PASSWORD)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"));
        mockMvc.perform(post("/sign-up")
                        .param("username", VALID_USERNAME)
                        .param("email", INVALID_EMAIL)
                        .param("password", VALID_PASSWORD)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"));
        mockMvc.perform(post("/sign-up")
                        .param("username", VALID_USERNAME)
                        .param("email", VALID_EMAIL)
                        .param("password", INVALID_PASSWORD)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"));
    }

    @DisplayName("Sign Up with valid input")
    @Test
    void signUpSubmit_with_valid_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                        .param("username", VALID_USERNAME)
                        .param("email", VALID_EMAIL)
                        .param("password", VALID_PASSWORD)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        // then
        Account accountByUsername = accountRepository.findByUsername(VALID_USERNAME);
        assertNotNull(accountByUsername);
        Account accountByEmail = accountRepository.findByEmail(VALID_EMAIL);
        assertNotNull(accountByEmail);
        assertEquals(accountByUsername.getUsername(), accountByEmail.getUsername());
        // then; Is the password encoded?
        assertNotEquals(accountByUsername.getPassword(), VALID_PASSWORD);
    }

}