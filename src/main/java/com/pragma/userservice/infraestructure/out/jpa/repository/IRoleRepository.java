package com.pragma.userservice.infraestructure.out.jpa.repository;

import com.pragma.userservice.infraestructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {

}
