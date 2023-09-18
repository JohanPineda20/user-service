package com.pragma.userservice.domain.api;

import com.pragma.userservice.domain.model.UserModel;

public interface IUserServicePort {
    void save(UserModel userModel);
}
