package com.ogunkuade.microservicesmanager.dto;


import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BNSProductUpdateRequestDto {

    //PRODUCT
    @NotBlank(message = "product name must not be blank")
    @NotEmpty(message = "product name must not be empty")
    @NotNull(message = "product name must not be null")
    @Size(min = 3, max = 20, message = "product name must be atleast 3 characters and not more than 20 characters")
    private String name;

    @NotBlank(message = "product description must not be blank")
    @NotEmpty(message = "product description must not be empty")
    @NotNull(message = "product description must not be null")
    @Size(min = 3, max = 20, message = "product description must be atleast 3 characters and not more than 20 characters")
    private String description;

    @NotBlank(message = "product amount must not be blank")
    @NotEmpty(message = "product amount must not be empty")
    @NotNull(message = "product amount must not be null")
    private String amount;

    @NotBlank(message = "product category must not be blank")
    @NotEmpty(message = "product category must not be empty")
    @NotNull(message = "product category must not be null")
    private String category;

//    //PRODUCT IMAGE
//    private String productImage_name;
//    private byte[] productImage_image;



}
