package com.ogunkuade.images.repository;


import com.ogunkuade.images.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    UserImage findImageById(Long id);

    UserImage findUserImageByUserId(Long id);



}


