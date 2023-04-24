package com.ogunkuade.microservicesmanager.dto;


import com.ogunkuade.microservicesmanager.passwordvalidation.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BNSUserRequestDto {

    //USER
    private UserRequestDto userRequestDto;
    //ADDRESS
    private AddressRequestDto addressRequestDto;
    //IMAGE
    private ImageRequestRecord imageRequestRecord;


}
