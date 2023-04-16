package com.ogunkuade.productservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductRequest {

    @NotBlank(message = "name must not be blank")
    @NotEmpty(message = "name must not be empty")
    @NotNull(message = "name must not be null")
    @Size(min = 4, max = 30, message = "name must be atleast 4 characters and not more than 30 characters")
    private String name;

    @NotBlank(message = "description must not be blank")
    @NotEmpty(message = "description must not be empty")
    @NotNull(message = "description must not be null")
    @Size(min = 4, max = 200, message = "description must be atleast 4 characters and not more than 200 characters")
    private String description;

    @NotBlank(message = "amount must not be blank")
    @NotEmpty(message = "amount must not be empty")
    @NotNull(message = "amount must not be null")
    private String amount;

    @NotBlank(message = "category must not be blank")
    @NotEmpty(message = "catgeory must not be empty")
    @NotNull(message = "category must not be null")
    @Size(min = 4, max = 20, message = "category must be atleast 4 characters and not more than 200 characters")
    private String category;

    private Long sellerId;

}

