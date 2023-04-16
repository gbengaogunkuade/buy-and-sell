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
    private String type;
    private Long userId;
    @Lob
    private byte[] image;



}
