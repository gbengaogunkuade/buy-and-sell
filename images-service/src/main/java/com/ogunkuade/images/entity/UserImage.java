package com.ogunkuade.images.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] image;
    private Long userId;



}
