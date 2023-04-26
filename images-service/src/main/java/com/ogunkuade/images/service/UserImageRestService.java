package com.ogunkuade.images.service;


import com.ogunkuade.images.dto.ImageRequestRecord;
import com.ogunkuade.images.dto.UserImageResponse;
import com.ogunkuade.images.entity.UserImage;
import com.ogunkuade.images.repository.UserImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserImageRestService {

    Logger logger = LoggerFactory.getLogger(UserImageRestService.class);


    String CURRENT_DIR = System.getProperty("user.dir");
    Path IMAGE_PATH = Path.of(CURRENT_DIR + "/src/main/resources/static/images");

    Path UPLOAD_PATH;
    Path localImage;
    String imageIDStr;

    UserImage userImage;
    UserImage savedUserImage;
    List<UserImage> userImageList;

    Path createLocalImage;
    String nameOfCreatedLocalImage;

    UserImageResponse userImageResponse;
    List<UserImageResponse> userImageResponseList;


    private final UserImageRepository userImageRepository;

    public UserImageRestService(UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }




    //POST SINGLE IMAGES
    public UserImageResponse imageRestUpload(@RequestBody ImageRequestRecord imageRequestRecord, Long id) throws IOException {
        if(userImageRepository.existsByUserId(id)){
            logger.warn("IMAGE FOR THIS USER ALREADY EXIST, OVERWRITING PREVIOUS IMAGE");
            userImage = userImageRepository.findImageByUserId(id);
            userImage.setName(imageRequestRecord.name());
            userImage.setImage(imageRequestRecord.image());
            UserImage savedImage = userImageRepository.save(userImage);
            userImageResponse = new UserImageResponse();
            userImageResponse.setId(savedImage.getId());
            userImageResponse.setName(savedImage.getName());
            userImageResponse.setImage(savedImage.getImage());
            userImageResponse.setUserId(savedImage.getUserId());
            return userImageResponse;
        } else{
            //SAVING IMAGES TO DATABASE
            userImage = new UserImage();
            userImage.setName(imageRequestRecord.name());
            userImage.setImage(imageRequestRecord.image());
            userImage.setUserId(id);
            UserImage savedImage = userImageRepository.save(userImage);
            userImageResponse = new UserImageResponse();
            userImageResponse.setId(savedImage.getId());
            userImageResponse.setName(savedImage.getName());
            userImageResponse.setImage(savedImage.getImage());
            userImageResponse.setUserId(savedImage.getUserId());
            return userImageResponse;
        }

    }




    //GET SINGLE IMAGE
    public UserImageResponse getRestImage(Long id) throws IOException {
        userImage = userImageRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Image not found"));
        userImageResponse = new UserImageResponse();
        userImageResponse.setId(userImage.getId());
        userImageResponse.setName(userImage.getName());
        userImageResponse.setImage(userImage.getImage());
        userImageResponse.setUserId(userImage.getUserId());
        return userImageResponse;
    }



    //GET SINGLE IMAGE BY USER ID
    public UserImageResponse getRestImageByUserId(Long id) throws IOException {
        if(userImageRepository.existsByUserId(id)){
            userImage = userImageRepository.findImageByUserId(id);
            userImageResponse = new UserImageResponse();
            userImageResponse.setId(userImage.getId());
            userImageResponse.setName(userImage.getName());
            userImageResponse.setImage(userImage.getImage());
            userImageResponse.setUserId(userImage.getUserId());
            return userImageResponse;
        } else{
            throw new FileNotFoundException("Image with this userId not found");
        }
    }



    //GET ALL IMAGES
    public List<UserImageResponse> getAllRestImage() {
        userImageResponseList = new ArrayList<>();
        userImageList = userImageRepository.findAll();
        for(UserImage userImage : userImageList){
            userImageResponse = new UserImageResponse();
            userImageResponse.setId(userImage.getId());
            userImageResponse.setName(userImage.getName());
            userImageResponse.setImage(userImage.getImage());
            userImageResponse.setUserId(userImage.getUserId());
            userImageResponseList.add(userImageResponse);
        }
        return userImageResponseList;
    }



    //GET SAMPLE IMAGE FOR TESTING
    public byte[] getSampleImage(Long id) throws FileNotFoundException {
        userImage = userImageRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Image Not Found"));
        return userImage.getImage();
    }







}
