package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.request.UserRequest;
import com.pragma.userservice.application.dto.response.UserResponse;


public interface IUserHandler {
    void save(UserRequest userRequest);

    UserResponse findById(Long id);
}
