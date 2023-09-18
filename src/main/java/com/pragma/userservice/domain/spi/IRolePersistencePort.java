package com.pragma.userservice.domain.spi;

import com.pragma.userservice.domain.model.RoleModel;

import java.util.List;

public interface IRolePersistencePort {
    void save(RoleModel roleModel);

    boolean existsByName(String name);

    List<RoleModel> getAllRoles();

    RoleModel findById(Long id);

    void delete(Long id);
}
