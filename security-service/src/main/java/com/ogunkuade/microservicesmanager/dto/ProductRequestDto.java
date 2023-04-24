package com.ogunkuade.microservicesmanager.dto;


import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductRequestDto {

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

    @NotBlank(message = "sellerId must not be blank")
    @NotEmpty(message = "sellerId must not be empty")
    @NotNull(message = "sellerId must not be null")
    @Min(value = 1L, message = "sellerId must be greater than or equal to 1")
    @Max(value = 9999999999L, message = "sellerId must be less than or equal to 9999999999")
    private Long sellerId;

}


