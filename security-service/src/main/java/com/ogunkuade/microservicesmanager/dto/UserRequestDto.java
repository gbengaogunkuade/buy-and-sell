package com.ogunkuade.microservicesmanager.dto;


import com.ogunkuade.microservicesmanager.passwordvalidation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRequestDto {

    @NotBlank(message = "username must not be blank")
    @Size(min = 3, max = 20, message = "username must be atleast 3 characters and not more than 20 characters")
    private String username;

    @ValidPassword      //custom password validator
    @NotBlank(message = "password must not be blank")
    @Size(min = 7, max = 12, message = "password must be atleast 7 characters and not more than 12 characters")
    private String password;

    @NotBlank(message = "password2 must not be blank")
    @Size(min = 7, max = 12, message = "password2 must be atleast 7 characters and not more than 12 characters")
    private String password2;

    @NotBlank(message = "firstname must not be blank")
    @Size(min = 4, max = 20, message = "firstname must be atleast 4 characters and not more than 20 characters")
    private String firstname;

    @NotBlank(message = "lastname must not be blank")
    @Size(min = 4, max = 20, message = "lastname must be atleast 4 characters and not more than 20 characters")
    private String lastname;

    @NotBlank(message = "gender must not be blank")
    private String gender;

    @NotBlank(message = "email must not be blank")
    @Email
//    @Pattern(regexp = "^[0-9A-Z@#$&%*]+$", message = "This accepts 1 range or more than 1 range")
    private String email;




}
