package com.pragma.userservice.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {

    @NotBlank(message = "name cannot be empty")
    private String name;
    private String lastname;
    @NotBlank(message = "document number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "document number must have exactly 10 numerical characters")
    private String documentNumber;
    @NotBlank(message = "cellphone cannot be empty")
    @Pattern(regexp = "^\\+?[0-9]{1,12}$", message = "cellphone is invalid")
    private String cellphone;
    @NotBlank(message = "date of birth cannot be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "date of birth is invalid" )
    private String birthdate;
    @NotBlank(message = "email cannot be empty")
    @Email(message = "email is invalid")
    private String email;
    @NotBlank(message = "password cannot be empty")
    private String password;
    @Min(value = 1, message = "role_id must be a positive number")
    private Long roleId;
}
