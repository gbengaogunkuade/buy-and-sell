package com.ogunkuade.microservicesmanager.dto;


import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductImageResponseDto {

    private Long id;
    private String name;
    private byte[] image;
    private Long productId;




}
