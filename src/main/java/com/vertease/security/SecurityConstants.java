package com.vertease.security;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class SecurityConstants {
    public static final String SECRET_KEY = "your_jwt_secret_key_here";
    public static final long EXPIRATION_TIME = 172800000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/auth/**";
}
