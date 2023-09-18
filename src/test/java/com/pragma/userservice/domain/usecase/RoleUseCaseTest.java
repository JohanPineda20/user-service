package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.exception.DataAlreadyExistsException;
import com.pragma.userservice.domain.exception.DataNotFoundException;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RoleUseCaseTest {

    @InjectMocks
    RoleUseCase roleUseCase;

    @Mock
    IRolePersistencePort rolePersistencePort;

    @Test
    void save() {
        RoleModel roleModel = new RoleModel();

        roleUseCase.save(roleModel);

        verify(rolePersistencePort, times(1)).save(roleModel);
    }
    void saveRoleAlreadyExists() {
        RoleModel roleModel = new RoleModel();
        when(rolePersistencePort.existsByName(roleModel.getName())).thenReturn(true);

        assertThrows(DataAlreadyExistsException.class, () -> roleUseCase.save(roleModel));
        verify(rolePersistencePort, never()).save(roleModel);
    }

    @Test
    void getAllRoles() {
        List<RoleModel> roleModelList = Arrays.asList(new RoleModel(1L,"ADMIN","administrator"), new RoleModel(2L,"OWNER","owner"));
        when(rolePersistencePort.getAllRoles()).thenReturn(roleModelList);

        List<RoleModel> roleModelList1 = roleUseCase.getAllRoles();

        assertEquals(roleModelList, roleModelList1);
        verify(rolePersistencePort, times(1)).getAllRoles();
    }

    @Test
    void getAllRolesEmpty() {
        when(rolePersistencePort.getAllRoles()).thenReturn(new ArrayList<>());

        assertThrows(DataNotFoundException.class, () -> roleUseCase.getAllRoles());
    }

    @Test
    void delete() {
        RoleModel roleModel = new RoleModel();
        when(rolePersistencePort.findById(1L)).thenReturn(roleModel);

        roleUseCase.delete(1L);

        verify(rolePersistencePort, times(1)).delete(1L);
    }

    @Test
    void deleteNotFound() {
        when(rolePersistencePort.findById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> roleUseCase.delete(1L));
        verify(rolePersistencePort, never()).delete(1L);
    }
}