package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.request.UserRequest;


public interface IUserHandler {
    void save(UserRequest userRequest);
}
