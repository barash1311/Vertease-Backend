package com.vertease.dto.login;

import com.vertease.dto.register.RegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    public String accessToken;
    private String refreshToken;
    private RegisterResponse user;
}
