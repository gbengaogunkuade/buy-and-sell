package com.ogunkuade.images.repository;


import com.ogunkuade.images.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    ProductImage findImageById(Long id);
    ProductImage findImageByName(String name);
    Boolean existsByName(String name);
//    ProductImage findProductImageByProductId(Long id);

}