package com.pragma.userservice.application.handler.impl;

import com.pragma.userservice.application.dto.request.RoleRequest;
import com.pragma.userservice.application.dto.response.RoleResponse;
import com.pragma.userservice.application.handler.IRoleHandler;
import com.pragma.userservice.application.mapper.IRoleDtoMapper;
import com.pragma.userservice.domain.api.IRoleServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleHandlerImpl implements IRoleHandler {
    private final IRoleServicePort roleServicePort;
    private final IRoleDtoMapper roleDtoMapper;
    @Override
    public void save(RoleRequest roleRequest) {
        roleServicePort.save(roleDtoMapper.mapToRoleModel(roleRequest));
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleDtoMapper.mapToRolResponseDtoList(roleServicePort.getAllRoles());
    }

    @Override
    public void delete(Long id) {
        roleServicePort.delete(id);
    }
}
