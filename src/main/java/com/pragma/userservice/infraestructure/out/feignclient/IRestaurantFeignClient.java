package com.pragma.userservice.infraestructure.out.feignclient;

import com.pragma.userservice.infraestructure.out.feignclient.dto.RestaurantEmployeeFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "foodcourt-service", url ="localhost:8081/restaurant")
public interface IRestaurantFeignClient {

    @PostMapping("/employee")
    void saveRestaurantEmployee(@RequestBody RestaurantEmployeeFeignDto restaurantEmployeeFeignDto);
}
