package com.pragma.userservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoleResponse {
    private Long id;
    private String name;
    private String description;
}
