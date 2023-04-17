package com.ogunkuade.images.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserImageResponse {

    private Long id;
    private Long userId;
    private byte[] image;


}
