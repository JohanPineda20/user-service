package com.pragma.userservice.infraestructure.out.jpa.repository;

import com.pragma.userservice.infraestructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {

}
