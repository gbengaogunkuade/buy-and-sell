package com.ogunkuade.microservicesmanager.dto;


import lombok.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BNSProductRequest {

    //USER
    private String username;

    //PRODUCT
    private String product_name;
    private String product_description;
    private String product_amount;
    private String product_category;
    private Long product_sellerId;




}


