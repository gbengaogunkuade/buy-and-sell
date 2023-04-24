package com.ogunkuade.microservicesmanager.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressResponseDto {

    private String lane1;
    private String lane2;
    private Long zip;
    private String state;


}
