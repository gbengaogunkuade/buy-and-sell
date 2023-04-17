package com.ogunkuade.images.dto;


import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductImageResponse {

    private Long id;
    private String name;
    private byte[] image;
    private Long productId;





}
