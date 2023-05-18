package com.ogunkuade.microservicesmanager.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GUI_ProductResponse {

    private Long id;
    private String productName;
    private String productDescription;
    private String productAmount;
    private String productCategory;
    private Boolean productAvailable;
    private Long productSellerId;

    private List<GUI_ProductImageResponse> guiProductImageResponseList;



}
