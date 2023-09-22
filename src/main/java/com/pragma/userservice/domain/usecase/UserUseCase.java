package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.exception.DataAlreadyExistsException;
import com.pragma.userservice.domain.exception.DataNotFoundException;
import com.pragma.userservice.domain.exception.DomainException;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.*;
import feign.FeignException;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IPasswordEncryptionPort passwordEncryptionPort;
    private final ISecurityContextPort securityContextPort;
    private final IRestaurantFeignClientPort restaurantFeignClientPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IPasswordEncryptionPort passwordEncryptionPort, ISecurityContextPort securityContextPort, IRestaurantFeignClientPort restaurantFeignClientPort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncryptionPort = passwordEncryptionPort;
        this.securityContextPort = securityContextPort;
        this.restaurantFeignClientPort = restaurantFeignClientPort;
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

        RoleModel roleModel = rolePersistencePort.findById(validateRole(userModel));
        if(roleModel==null){throw new DataNotFoundException("Role not found");}
        userModel.setRole(roleModel);

        userModel.setPassword(passwordEncryptionPort.encode(userModel.getPassword()));
        UserModel userModel1 = userPersistencePort.save(userModel);

        if(roleModel.getId() == 3L) saveEmployee(userModel1.getId());

    }
    @Override
    public UserModel findById(Long id) {
        UserModel userModel = userPersistencePort.findById(id);
        if(userModel==null) throw new DataNotFoundException("User not found");
        return userModel;
    }


    private int calculateAge(LocalDate birthdate){
        return LocalDate.now().getYear()-birthdate.getYear();
    }

    private Long validateRole(UserModel userModel) {
        if(userModel.getRole().getId() != null){
            if(userModel.getRole().getId() == 3L && getRolFromSecurityContext().equals("OWNER")) return 3L;
            throw new DomainException("Operation not allowed. You can't create an user " + (userModel.getRole().getId() == 3L ? "EMPLOYEE" : userModel.getRole().getId() == 4L ? "CUSTOMER" : userModel.getRole().getId() == 1L ? "ADMIN" : userModel.getRole().getId() == 2L ? "OWNER" : "because that role not exists"));
        }
        if(getRolFromSecurityContext().equals("ADMIN")) return 2L;
        throw new DomainException("Operation not allowed. You can't create an user OWNER");
    }
    private String getRolFromSecurityContext(){
        return securityContextPort.getRolFromSecurityContext();
    }

    private void saveEmployee(Long employeeId) {
        Long ownerId = securityContextPort.getIdFromSecurityContext();
        try {
            restaurantFeignClientPort.saveRestaurantEmployee(ownerId, employeeId);
        }
        catch(FeignException.NotFound e){
            throw new DataNotFoundException("Owner does not have a restaurant");
        }
        catch (FeignException e){
            throw new DomainException(e.getMessage());
        }
    }

}
