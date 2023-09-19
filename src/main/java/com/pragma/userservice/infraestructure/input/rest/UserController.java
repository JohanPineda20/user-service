package com.pragma.userservice.infraestructure.input.rest;

import com.pragma.userservice.application.dto.request.UserRequest;
import com.pragma.userservice.application.dto.response.UserResponse;
import com.pragma.userservice.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request: wrong input data", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception"))),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception"))),
            @ApiResponse(responseCode = "409", description = "Conflict: user already exists, age not allowed ", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception")))
    })
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UserRequest userRequest){
        userHandler.save(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get an user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get an user", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception"))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(userHandler.findById(id));
    }

}
