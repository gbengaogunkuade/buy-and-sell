package com.ogunkuade.microservicesmanager.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String amount;
    private String category;
    private Boolean available;
    private Long sellerId;


}
