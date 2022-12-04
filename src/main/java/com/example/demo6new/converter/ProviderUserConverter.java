package com.example.demo6new.converter;

public interface ProviderUserConverter<T,R> {
    R convert(T t);
}
