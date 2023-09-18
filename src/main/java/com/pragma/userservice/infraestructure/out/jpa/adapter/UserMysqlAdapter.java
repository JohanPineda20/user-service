package com.pragma.userservice.infraestructure.out.jpa.adapter;

import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.infraestructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.userservice.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return userRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(UserModel userModel) {
        userRepository.save(userEntityMapper.mapToUserEntity(userModel));
    }
}
