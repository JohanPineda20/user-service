package com.pragma.userservice.infraestructure.security.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequest {
    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
}
