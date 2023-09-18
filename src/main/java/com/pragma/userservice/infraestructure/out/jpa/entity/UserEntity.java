package com.pragma.userservice.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String lastname;
    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;
    @Column(nullable = false)
    private String cellphone;
    @Column(nullable = false)
    private LocalDate birthdate;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private RoleEntity role;
}
