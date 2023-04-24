package com.ogunkuade.microservicesmanager.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserImageResponseDto {

    private Long id;
    private String name;
    private byte[] image;
    private Long userId;

}
