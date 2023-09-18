package com.pragma.userservice.infraestructure.out.passwordencryption;

import com.pragma.userservice.domain.spi.IPasswordEncryptionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PasswordEncryptionAdapter implements IPasswordEncryptionPort {

    private final PasswordEncoder passwordEncoder;
    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
