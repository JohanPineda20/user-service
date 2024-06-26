package com.pragma.userservice.infraestructure.input.rest;

import com.pragma.userservice.application.dto.request.RoleRequest;
import com.pragma.userservice.application.dto.response.RoleResponse;
import com.pragma.userservice.application.handler.IRoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Role Controller")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleHandler roleHandler;

    @Operation(summary = "Add a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request: wrong input data", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception"))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception"))),
            @ApiResponse(responseCode = "409", description = "Conflict: role already exists", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception")))
    })
    @SecurityRequirement(name = "jwt")
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> save(@Valid @RequestBody RoleRequest roleRequest){
        roleHandler.save(roleRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all roles", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoleResponse.class)))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception"))),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception")))
    })
    @SecurityRequirement(name = "jwt")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RoleResponse>> getAllRoles(){
        return new ResponseEntity<>(roleHandler.getAllRoles(), HttpStatus.OK);
    }

    @Operation(summary = "Delete a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role deleted from database", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception"))),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Exception")))
    })
    @SecurityRequirement(name = "jwt")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        roleHandler.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
