package com.ogunkuade.microservicesmanager.dto;


import com.ogunkuade.microservicesmanager.passwordvalidation.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BNSProductRequestDto {

    //PRODUCT
    private ProductRequestDto productRequestDto;
    //IMAGE
    private ImageRequestRecord imageRequestRecord;
    //USER
    private UserRequestDto userRequestDto;

}
