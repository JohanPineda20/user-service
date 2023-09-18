package com.pragma.userservice.domain.model;

import java.time.LocalDate;

public class UserModel {

    private Long id;
    private String name;
    private String lastname;
    private String documentNumber;
    private String cellphone;
    private LocalDate birthdate;
    private String email;
    private String password;
    private RoleModel role;

    public UserModel(){}
    public UserModel(Long id, String name, String lastname, String documentNumber, String cellphone, LocalDate birthdate, String email, String password, RoleModel role) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.documentNumber = documentNumber;
        this.cellphone = cellphone;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }
}
