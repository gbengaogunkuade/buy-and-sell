package com.ogunkuade.images.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "images")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Long productId;
    @Lob
    private byte[] image;

    public Image(String name, String type, Long productId, byte[] image) {
        this.name = name;
        this.type = type;
        this.productId = productId;
        this.image = image;
    }


}
