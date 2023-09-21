package com.pragma.userservice.infraestructure.security.auth;

import com.pragma.userservice.infraestructure.security.auth.dto.LoginRequest;
import com.pragma.userservice.infraestructure.security.auth.dto.LoginResponse;
import com.pragma.userservice.infraestructure.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtProvider.createToken(authentication);
        return new LoginResponse(jwt);
    }
}
