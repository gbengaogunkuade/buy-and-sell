package com.ogunkuade.productservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String amount;
    private String category;
    private Boolean available;
    private Long sellerId;
    @Lob
    private List<byte[]> imageList;


}
