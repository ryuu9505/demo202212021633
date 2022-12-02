package com.example.demo6new.domain.form;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class ClientUser {

    private String username;
    private String password;
    private String email;
    private String picture;

    private List<? extends GrantedAuthority> authorities;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
