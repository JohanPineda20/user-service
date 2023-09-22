package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.exception.DataAlreadyExistsException;
import com.pragma.userservice.domain.exception.DataNotFoundException;
import com.pragma.userservice.domain.exception.DomainException;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.*;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserUseCaseTest {
    @InjectMocks
    UserUseCase userUseCase;

    @Mock
    IUserPersistencePort userPersistencePort;

    @Mock
    IRolePersistencePort rolePersistencePort;

    @Mock
    IPasswordEncryptionPort passwordEncryptionPort;

    @Mock
    ISecurityContextPort securityContextPort;
    @Mock
    IRestaurantFeignClientPort restaurantFeignClientPort;

    @Test
    void saveOwner() {
        UserModel userModel = createExampleUser();
        userModel.setRole(new RoleModel());
        RoleModel roleModel1 = createExampleRole(2L);
        when(passwordEncryptionPort.encode(userModel.getPassword())).thenReturn("password");
        when(securityContextPort.getRolFromSecurityContext()).thenReturn("ADMIN");
        when(rolePersistencePort.findById(2L)).thenReturn(roleModel1);

        userUseCase.save(userModel);

        assertEquals("password", userModel.getPassword());
        assertEquals(roleModel1, userModel.getRole());
        verify(userPersistencePort, times(1)).save(userModel);
    }

    @Test
    void saveOwnerNotAllowed() {
        UserModel userModel = createExampleUser();
        userModel.setRole(new RoleModel());
        when(securityContextPort.getRolFromSecurityContext()).thenReturn("CUSTOMER");
        when(rolePersistencePort.findById(2L)).thenReturn(any());

        assertThrows(DomainException.class, ()->userUseCase.save(userModel));
        verify(userPersistencePort, never()).save(userModel);
    }

    @Test
    void saveEmployee() {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(3L);
        UserModel userModel = createExampleUser();
        userModel.setRole(roleModel);
        RoleModel roleModel1 = createExampleRole(3L);
        when(passwordEncryptionPort.encode(userModel.getPassword())).thenReturn("password");
        when(securityContextPort.getRolFromSecurityContext()).thenReturn("OWNER");
        when(rolePersistencePort.findById(3L)).thenReturn(roleModel1);
        UserModel userModel1 = createExampleUser();
        userModel1.setId(1L);
        when(userPersistencePort.save(userModel)).thenReturn(userModel1);
        when(securityContextPort.getIdFromSecurityContext()).thenReturn(1L);

        userUseCase.save(userModel);

        assertEquals("password", userModel.getPassword());
        assertEquals(roleModel1, userModel.getRole());
        verify(userPersistencePort, times(1)).save(userModel);
        verify(restaurantFeignClientPort, times(1)).saveRestaurantEmployee(1L, userModel1.getId());
    }

    @Test
    void saveEmployeeNotAllowed() {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(3L);
        UserModel userModel = createExampleUser();
        userModel.setRole(roleModel);
        when(securityContextPort.getRolFromSecurityContext()).thenReturn("ADMIN");
        when(rolePersistencePort.findById(3L)).thenReturn(any());
        UserModel userModel1 = createExampleUser();
        userModel1.setId(1L);
        when(userPersistencePort.save(userModel)).thenReturn(userModel1);
        when(securityContextPort.getIdFromSecurityContext()).thenReturn(1L);

        assertThrows(DomainException.class, ()->userUseCase.save(userModel));
        verify(userPersistencePort, never()).save(userModel);
        verify(restaurantFeignClientPort, never()).saveRestaurantEmployee(1L, userModel1.getId());
    }

    @Test
    void saveEmployeeFeignExceptionNotFound() {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(3L);
        UserModel userModel = createExampleUser();
        userModel.setRole(roleModel);
        RoleModel roleModel1 = createExampleRole(3L);
        when(passwordEncryptionPort.encode(userModel.getPassword())).thenReturn("password");
        when(securityContextPort.getRolFromSecurityContext()).thenReturn("OWNER");
        when(rolePersistencePort.findById(3L)).thenReturn(roleModel1);
        UserModel userModel1 = createExampleUser();
        userModel1.setId(1L);
        when(userPersistencePort.save(userModel)).thenReturn(userModel1);
        when(securityContextPort.getIdFromSecurityContext()).thenReturn(1L);
        doThrow(FeignException.NotFound.class).when(restaurantFeignClientPort).saveRestaurantEmployee(1L, userModel1.getId());

        assertThrows(DataNotFoundException.class ,()->userUseCase.save(userModel));
        assertEquals("password", userModel.getPassword());
        assertEquals(roleModel1, userModel.getRole());
        verify(userPersistencePort, times(1)).save(userModel);
        verify(restaurantFeignClientPort, times(1)).saveRestaurantEmployee(1L, userModel1.getId());
    }

    @Test
    void saveEmployeeFeignException() {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(3L);
        UserModel userModel = createExampleUser();
        userModel.setRole(roleModel);
        RoleModel roleModel1 = createExampleRole(3L);
        when(passwordEncryptionPort.encode(userModel.getPassword())).thenReturn("password");
        when(securityContextPort.getRolFromSecurityContext()).thenReturn("OWNER");
        when(rolePersistencePort.findById(3L)).thenReturn(roleModel1);
        UserModel userModel1 = createExampleUser();
        userModel1.setId(1L);
        when(userPersistencePort.save(userModel)).thenReturn(userModel1);
        when(securityContextPort.getIdFromSecurityContext()).thenReturn(1L);
        doThrow(FeignException.class).when(restaurantFeignClientPort).saveRestaurantEmployee(1L, userModel1.getId());

        assertThrows(DomainException.class ,()->userUseCase.save(userModel));
        assertEquals("password", userModel.getPassword());
        assertEquals(roleModel1, userModel.getRole());
        verify(userPersistencePort, times(1)).save(userModel);
        verify(restaurantFeignClientPort, times(1)).saveRestaurantEmployee(1L, userModel1.getId());
    }


    @Test
    void saveUserAlreadyExists() {
        //by email
        UserModel userModel = createExampleUser();
        when(userPersistencePort.existsByEmail(userModel.getEmail())).thenReturn(true);

        assertThrows(DataAlreadyExistsException.class, () -> userUseCase.save(userModel));
        verify(userPersistencePort, never()).save(userModel);

        //by documentNumber
        when(userPersistencePort.existsByDocumentNumber(userModel.getDocumentNumber())).thenReturn(true);

        assertThrows(DataAlreadyExistsException.class, () -> userUseCase.save(userModel));
        verify(userPersistencePort, never()).save(userModel);
    }
    @Test
    void saveAgeNotAllowed() {
        UserModel userModel = createExampleUser();
        userModel.setBirthdate(LocalDate.of(2020,10,10));

        assertThrows(DomainException.class, () -> userUseCase.save(userModel));
        verify(userPersistencePort, never()).save(userModel);
    }

    @Test
    void findById(){
        UserModel userModel = createExampleUser();
        when(userPersistencePort.findById(1L)).thenReturn(userModel);

        UserModel userModel1 = userUseCase.findById(1L);

        assertEquals(userModel, userModel1);
        assertDoesNotThrow(()->userUseCase.findById(1L));
    }

    @Test
    void findByIdUserNotFound(){
        assertThrows(DataNotFoundException.class,()->userUseCase.findById(1L));
    }

    private UserModel createExampleUser(){
        UserModel userModel = new UserModel();
        userModel.setName("Johan");
        userModel.setLastname("Pineda");
        userModel.setDocumentNumber("1004929223");
        userModel.setCellphone("3122111");
        userModel.setBirthdate(LocalDate.of(2001,10,16));
        userModel.setEmail("johan@gmail.com");
        userModel.setPassword("1234");
        return userModel;
    }
    private RoleModel createExampleRole(Long id){
        RoleModel roleModel = new RoleModel();
        roleModel.setId(id);
        return roleModel;
    }

}