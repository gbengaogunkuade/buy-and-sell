package com.ogunkuade.microservicesmanager.dto;

import com.ogunkuade.microservicesmanager.entity.Role;
import lombok.*;

import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BNSResponse {

    //USER
    private Long id;
    private String username;
    private String password;
    private String password2;
    private String firstname;
    private String lastname;
    private String gender;
    private String email;
    private Collection<Role> roles;

    //PRODUCT
    private String product_name;
    private String product_description;
    private String product_amount;
    private String product_category;
    private Long product_sellerId;
    private List<byte[]> product_imageList;



}


