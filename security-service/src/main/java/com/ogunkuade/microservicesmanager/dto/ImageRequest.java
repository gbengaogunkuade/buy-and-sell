package com.ogunkuade.microservicesmanager.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageRequest {

    private String name;
    private byte[] image;

}
