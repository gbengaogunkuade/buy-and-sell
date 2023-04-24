package com.ogunkuade.microservicesmanager.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDto {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String gender;
    private String email;

}
