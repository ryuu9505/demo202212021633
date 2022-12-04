package com.example.demo6new.converter;

import com.example.demo6new.domain.ProviderUser;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public final class DelegatingProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    private final List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> converters;

    public DelegatingProviderUserConverter() {

        List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> providerUserConverters = Arrays.asList(
                new UserDetailsProviderUserConverter(),
                new OAuth2GoogleProviderUserConverter(),
                new OAuth2NaverProviderUserConverter(),
                new OAuth2KakaoProviderUserConverter());

        this.converters = Collections.unmodifiableList(new LinkedList<>(providerUserConverters));
    }

    @Override
    @Nullable
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {
        Assert.notNull(providerUserRequest, "providerUserRequest cannot be null"); // todo exception handling
        for (ProviderUserConverter<ProviderUserRequest, ProviderUser> converter : this.converters) {
            ProviderUser providerUser = converter.convert(providerUserRequest);
            if (providerUser != null) {
                return providerUser;
            }
        }
        return null;
    }

}
