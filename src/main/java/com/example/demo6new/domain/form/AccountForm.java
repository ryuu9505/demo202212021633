package com.example.demo6new.domain.form;

import com.example.demo6new.domain.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class AccountForm {

    private String username;

    private String email;

    private Set<Role> roles = new HashSet<>();
}
