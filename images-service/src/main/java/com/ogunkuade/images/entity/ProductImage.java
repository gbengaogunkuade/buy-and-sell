package com.ogunkuade.images.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "products_images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] image;
    private Long productId;




}
