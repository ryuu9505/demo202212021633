package com.example.demo6new.converter;


import com.example.demo6new.domain.ProviderUser;
import com.example.demo6new.domain.form.NaverUser;
import com.example.demo6new.utility.OAuth2Config;
import com.example.demo6new.utility.OAuth2Utils;

public final class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest,ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.NAVER.getSocialName())) {
            return null;
        }

        return new NaverUser(OAuth2Utils.getSecondLayerAttributes(
                providerUserRequest.oAuth2User(), "response"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}
