package com.example.demo6new.converter;

import com.example.demo6new.domain.ProviderUser;
import com.example.demo6new.domain.form.GoogleUser;
import com.example.demo6new.utility.OAuth2Config;
import com.example.demo6new.utility.OAuth2Utils;

public final class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())) {
            return null;
        }

        return new GoogleUser(OAuth2Utils.getFirstLayerAttributes(
                providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());

    }
}
