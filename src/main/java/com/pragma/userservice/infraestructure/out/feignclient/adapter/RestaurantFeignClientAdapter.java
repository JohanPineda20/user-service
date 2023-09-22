package com.pragma.userservice.infraestructure.out.feignclient.adapter;

import com.pragma.userservice.domain.spi.IRestaurantFeignClientPort;
import com.pragma.userservice.infraestructure.out.feignclient.IRestaurantFeignClient;
import com.pragma.userservice.infraestructure.out.feignclient.dto.RestaurantEmployeeFeignDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantFeignClientAdapter implements IRestaurantFeignClientPort {

    private final IRestaurantFeignClient restaurantFeignClient;
    @Override
    public void saveRestaurantEmployee(Long ownerId, Long employeeId) {
        RestaurantEmployeeFeignDto restaurantEmployeeFeignDto = new RestaurantEmployeeFeignDto(ownerId, employeeId);
        restaurantFeignClient.saveRestaurantEmployee(restaurantEmployeeFeignDto);
    }
}
