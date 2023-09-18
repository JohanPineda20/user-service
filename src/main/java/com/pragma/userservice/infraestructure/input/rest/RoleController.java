package com.pragma.userservice.infraestructure.input.rest;

import com.pragma.userservice.application.dto.request.RoleRequest;
import com.pragma.userservice.application.dto.response.RoleResponse;
import com.pragma.userservice.application.handler.IRoleHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleHandler roleHandler; //toca instalar plugin de lombok en intellij community o sino me muestra error. En intellij ultimate no hay problema

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody RoleRequest roleRequest){
        roleHandler.save(roleRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles(){
        return new ResponseEntity<>(roleHandler.getAllRoles(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        roleHandler.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
