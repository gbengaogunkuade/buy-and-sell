package com.ogunkuade.microservicesmanager.dto;


import com.ogunkuade.microservicesmanager.passwordvalidation.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BNSUserResponseDto {

    //USER
    private UserResponseDto userResponseDto;
    //ADDRESS
    private AddressResponseDto addressResponseDto;
    //IMAGE
    private UserImageResponseDto imageResponseDto;

}
