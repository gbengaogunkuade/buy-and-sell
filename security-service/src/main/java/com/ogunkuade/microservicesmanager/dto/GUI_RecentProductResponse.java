package com.ogunkuade.microservicesmanager.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GUI_RecentProductResponse {

    private Long id;
    private String productName;
    private String productDescription;
    private String productAmount;
    private String productCategory;
    private Boolean productAvailable;
    private Long productSellerId;

    private GUI_ProductImageResponse gui_productImageResponse;


}


