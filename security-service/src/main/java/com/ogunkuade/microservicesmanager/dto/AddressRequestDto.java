package com.ogunkuade.microservicesmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressRequestDto {

    @NotBlank(message = "lane1 must not be blank")
    @NotEmpty(message = "lane1 must not be empty")
    @NotNull(message = "lane1 must not be null")
    @Size(min = 4, max = 20, message = "lane1 must be atleast 4 characters and not more than 20 characters")
    private String lane1;

    @NotBlank(message = "lane2 must not be blank")
    @NotEmpty(message = "lane2 must not be empty")
    @NotNull(message = "lane2 must not be null")
    @Size(min = 4, max = 20, message = "lane2 must be atleast 4 characters and not more than 20 characters")
    private String lane2;

    @NotNull(message = "zip must not be null")
    private Long zip;

    @NotBlank(message = "state must not be blank")
    @NotEmpty(message = "state must not be empty")
    @NotNull(message = "state must not be null")
    @Size(min = 2, max = 15, message = "state must be atleast 2 characters and not more than 15 characters")
    private String state;


}
