package com.example.demo6new.domain.form;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class NaverUser {

    private Map<String, Object> attributes;
    private OAuth2User oAuth2User;
    private ClientRegistration clientRegistration;

    public String getId() {
        return (String) attributes.get("id");
    }

    public String getUsername() {
        return (String) attributes.get("name");
    }

    public String getPicture() {
        return (String) attributes.get("profile_image");
    }

    public String getPassword() {
        return UUID.randomUUID().toString();
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getProvider() {
        return clientRegistration.getRegistrationId();
    }

    public List<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities().stream().map(authority ->
                new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
    }

}
