package com.pragma.userservice.application.handler.impl;

import com.pragma.userservice.application.dto.request.UserRequest;
import com.pragma.userservice.application.dto.response.UserResponse;
import com.pragma.userservice.application.handler.IUserHandler;
import com.pragma.userservice.application.mapper.IUserDtoMapper;
import com.pragma.userservice.domain.api.IUserServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserDtoMapper userDtoMapper;
    @Override
    public void save(UserRequest userRequest) {
       userServicePort.save(userDtoMapper.mapToUserModel(userRequest));
    }

    @Override
    public UserResponse findById(Long id) {
        return userDtoMapper.mapToUserResponse(userServicePort.findById(id));
    }
}
