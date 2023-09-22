package com.pragma.userservice.domain.spi;

public interface IRestaurantFeignClientPort {
    void saveRestaurantEmployee(Long ownerId, Long employeeId);
}
