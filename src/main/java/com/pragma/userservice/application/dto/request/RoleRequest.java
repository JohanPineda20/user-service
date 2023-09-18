package com.pragma.userservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoleRequest {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    private String description;
}
