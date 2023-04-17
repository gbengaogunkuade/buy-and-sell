package com.ogunkuade.images.repository;


import com.ogunkuade.images.entity.ProductImage;
import com.ogunkuade.images.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    UserImage findImageById(Long id);

    Boolean existsByUserId(Long id);

    UserImage findImageByUserId(Long id);



}


