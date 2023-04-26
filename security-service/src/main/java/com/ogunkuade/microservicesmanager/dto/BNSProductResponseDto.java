package com.ogunkuade.microservicesmanager.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BNSProductResponseDto {

    //PRODUCT
    private ProductResponseDto productResponseDto;
    //IMAGE
    private List<ProductImageResponseDto> productImageResponseDtoList;

}



