package com.ogunkuade.images.repository;


import com.ogunkuade.images.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    Boolean existsByProductId(Long id);

    List<ProductImage> findImagesByProductId(Long id);

}
