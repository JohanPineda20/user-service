package com.pragma.userservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {
    private Long id;
    private String name;
    private String lastname;
    private String documentNumber;
    private String cellphone;
    private String email;
    private String rol;
}
