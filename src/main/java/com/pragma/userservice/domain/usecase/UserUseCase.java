package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.exception.DataAlreadyExistsException;
import com.pragma.userservice.domain.exception.DataNotFoundException;
import com.pragma.userservice.domain.exception.DomainException;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.IPasswordEncryptionPort;
import com.pragma.userservice.domain.spi.IRolePersistencePort;
import com.pragma.userservice.domain.spi.IUserPersistencePort;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IPasswordEncryptionPort passwordEncryptionPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IPasswordEncryptionPort passwordEncryptionPort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncryptionPort = passwordEncryptionPort;
    }

    @Override
    public void save(UserModel userModel) {
        if(calculateAge(userModel.getBirthdate()) < 18){
            throw new DomainException("Age not allowed");
        }
        if(userPersistencePort.existsByDocumentNumber(userModel.getDocumentNumber())){
            throw new DataAlreadyExistsException("User with document number " + userModel.getDocumentNumber() + " already exists");
        }
        if(userPersistencePort.existsByEmail(userModel.getEmail())){
            throw new DataAlreadyExistsException("User with email " + userModel.getEmail() + " already exists");
        }
        RoleModel roleModel = rolePersistencePort.findById(2L);
        if(roleModel==null){
            throw new DataNotFoundException("Role not found");
        }
        userModel.setRole(roleModel);
        userModel.setPassword(passwordEncryptionPort.encode(userModel.getPassword()));

        userPersistencePort.save(userModel);
    }


    private int calculateAge(LocalDate birthdate){
        return LocalDate.now().getYear()-birthdate.getYear();
    }
}
