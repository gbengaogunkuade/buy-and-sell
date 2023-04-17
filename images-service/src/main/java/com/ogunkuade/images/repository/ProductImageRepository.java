package com.ogunkuade.images.repository;


import com.ogunkuade.images.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    ProductImage findImageById(Long id);

    Boolean existsByProductId(Long id);
//    ProductImage findProductImageByProductId(Long id);
    List<ProductImage> findImagesByProductId(Long id);

}
