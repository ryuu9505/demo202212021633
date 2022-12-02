package com.example.demo6new.domain.form;

import com.example.demo6new.domain.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class AccountSaveForm {

    @Pattern(regexp = "^[a-z0-9_-]{3,20}$")
    private String username;

    @Email
    @NotBlank
    private String email;

    @Length(min = 8, max = 50)
    private String password;

    @Nullable
    private Set<Role> roles;

}