package com.pragma.userservice.infraestructure.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private String jwt;
}
