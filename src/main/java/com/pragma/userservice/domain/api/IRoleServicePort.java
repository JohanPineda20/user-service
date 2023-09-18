package com.pragma.userservice.domain.api;

import com.pragma.userservice.domain.model.RoleModel;

import java.util.List;

public interface IRoleServicePort {
    void save(RoleModel roleModel);

    List<RoleModel> getAllRoles();

    void delete(Long id);
}
