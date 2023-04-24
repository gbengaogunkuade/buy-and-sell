package com.ogunkuade.microservicesmanager.dto;


import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BNSProductResponseDto {

    //PRODUCT
    private ProductResponseDto productResponseDto;
    //USER
    private UserResponseDto userResponseDto;
    //IMAGE
    private ProductImageResponseDto productImageResponseDto;

}



