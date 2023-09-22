package com.pragma.userservice.infraestructure.out.feignclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantEmployeeFeignDto {
    private Long ownerId;
    private Long employeeId;
}
