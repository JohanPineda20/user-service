package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.request.RoleRequest;
import com.pragma.userservice.application.dto.response.RoleResponse;

import java.util.List;

public interface IRoleHandler {

    void save(RoleRequest roleRequest);

    List<RoleResponse> getAllRoles();

    void delete(Long id);
}
