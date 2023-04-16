package com.ogunkuade.microservicesmanager.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class ImageResponse {

    private Long id;
    private String name;
    private String type;
    private Long productId;
    private byte[] image;


    public ImageResponse(String name, String type, Long productId, byte[] image) {
        this.name = name;
        this.type = type;
        this.productId = productId;
        this.image = image;
    }



}
