package com.ogunkuade.microservicesmanager.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private String amount;
    private String category;
    private Boolean available;
    private Long sellerId;


}
