package com.ogunkuade.images.repository;

import com.ogunkuade.images.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageById(Long id);
    Image findImageByName(String name);
    Boolean existsByName(String name);

}
