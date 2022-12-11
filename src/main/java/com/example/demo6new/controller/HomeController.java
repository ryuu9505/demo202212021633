package com.example.demo6new.controller;

import com.example.demo6new.domain.PrincipalUser;
import com.example.demo6new.domain.ProviderUser;
import com.example.demo6new.utility.CurrentAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.demo6new.utility.SecurityUrl.ACCESS_DENIED_URL;
import static com.example.demo6new.utility.SecurityUrl.ACCESS_DENIED_VIEW_NAME;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal PrincipalUser principalUser, Model model) {

        if (principalUser != null) {
            ProviderUser providerUser = principalUser.providerUser();
            model.addAttribute("username", providerUser.getUsername());
        }

        return "index";
    }

    @GetMapping("/api/user")
    public Authentication user(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
        log.info("authentication = {1}\noAuth2User ={2}", authentication, oAuth2User);
        return authentication;
    }

    @GetMapping("/api/oidc")
    public Authentication oidc(Authentication authentication, @AuthenticationPrincipal OidcUser oidcUser) {
        log.info("authentication = {1}\noidcUser ={2}", authentication, oidcUser);
        return authentication;
    }

    @GetMapping(ACCESS_DENIED_URL)
    public String denied(
            @CurrentAccount ProviderUser account, // todo test
            @RequestParam(value = "exception", required = false) String exception,
            Model model) {
        model.addAttribute("username", account.getUsername()); // todo test
        model.addAttribute("exception", exception);
        return ACCESS_DENIED_VIEW_NAME;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
