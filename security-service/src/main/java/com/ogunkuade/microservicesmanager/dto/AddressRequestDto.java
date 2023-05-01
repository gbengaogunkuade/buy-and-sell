package com.ogunkuade.microservicesmanager.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressRequestDto {

    @NotBlank(message = "lane1 must not be blank")
    @Size(min = 4, max = 20, message = "lane1 must be atleast 4 characters and not more than 20 characters")
    private String lane1;

    @NotBlank(message = "lane2 must not be blank")
    @Size(min = 4, max = 20, message = "lane2 must be atleast 4 characters and not more than 20 characters")
    private String lane2;

    @NotBlank(message = "zip must not be blank")
    @Pattern(regexp = "^[1-9]{6}$", message = "This must contain only 6 digits")
    private String zip;

    @NotBlank(message = "state must not be blank")
    @Size(min = 2, max = 15, message = "state must be atleast 2 characters and not more than 15 characters")
    private String state;


}
