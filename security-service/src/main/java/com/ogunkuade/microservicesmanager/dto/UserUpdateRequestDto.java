package com.ogunkuade.microservicesmanager.dto;


import com.ogunkuade.microservicesmanager.passwordvalidation.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateRequestDto {

    @NotBlank(message = "firstname must not be blank")
    @NotEmpty(message = "firstname must not be empty")
    @NotNull(message = "firstname must not be null")
    @Size(min = 4, max = 20, message = "firstname must be atleast 4 characters and not more than 20 characters")
    private String firstname;

    @NotBlank(message = "lastname must not be blank")
    @NotEmpty(message = "lastname must not be empty")
    @NotNull(message = "lastname must not be null")
    @Size(min = 4, max = 20, message = "lastname must be atleast 4 characters and not more than 20 characters")
    private String lastname;

    @NotBlank(message = "gender must not be blank")
    @NotEmpty(message = "gender must not be empty")
    @NotNull(message = "gender must not be null")
    private String gender;

    @NotBlank(message = "email must not be blank")
    @NotEmpty(message = "email must not be empty")
    @NotNull(message = "email must not be null")
    @Email
    private String email;



}
