package com.pragma.userservice.infraestructure.out.jpa.repository;

import com.pragma.userservice.infraestructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
