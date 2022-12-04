package com.example.demo6new.service.user;

import com.example.demo6new.converter.ProviderUserConverter;
import com.example.demo6new.converter.ProviderUserRequest;
import com.example.demo6new.domain.PrincipalUser;
import com.example.demo6new.domain.ProviderUser;
import com.example.demo6new.repository.AccountRepository;
import com.example.demo6new.service.AccountService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    public CustomOidcUserService(AccountService accountService, AccountRepository accountRepository, ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter) {
        super(accountService, accountRepository, providerUserConverter);
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration =
                ClientRegistration.withClientRegistration(userRequest.getClientRegistration())
                        .userNameAttributeName("sub")
                        .build();
        OidcUserRequest oidcUserRequest =
                new OidcUserRequest(clientRegistration, userRequest.getAccessToken(),
                        userRequest.getIdToken(), userRequest.getAdditionalParameters());
        OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();
        OidcUser oidcUser = oidcUserService.loadUser(oidcUserRequest);
        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oidcUser);
        ProviderUser providerUser = getProviderUser(providerUserRequest);
        register(providerUser);
        return new PrincipalUser(providerUser);
    }

}
