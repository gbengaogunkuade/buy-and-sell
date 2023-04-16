package com.ogunkuade.images.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserImageResponse {

    private Long id;
    private String name;
    private String type;
    private Long userId;
    private byte[] image;


}
