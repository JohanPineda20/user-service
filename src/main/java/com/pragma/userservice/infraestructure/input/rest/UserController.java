package com.pragma.userservice.infraestructure.input.rest;

import com.pragma.userservice.application.dto.request.UserRequest;
import com.pragma.userservice.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserRequest userRequest){
        userHandler.save(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
