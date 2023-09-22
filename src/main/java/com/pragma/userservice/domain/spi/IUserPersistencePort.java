package com.pragma.userservice.domain.spi;

import com.pragma.userservice.domain.model.UserModel;

public interface IUserPersistencePort {
    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmail(String email);

    UserModel save(UserModel userModel);

    UserModel findById(Long id);
}
