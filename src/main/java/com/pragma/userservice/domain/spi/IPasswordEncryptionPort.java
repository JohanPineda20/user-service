package com.pragma.userservice.domain.spi;

public interface IPasswordEncryptionPort {
    String encode(String password);
}
