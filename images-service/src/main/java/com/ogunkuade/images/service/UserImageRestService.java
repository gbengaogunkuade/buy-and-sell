package com.ogunkuade.images.service;


import com.ogunkuade.images.dto.UserImageResponse;
import com.ogunkuade.images.entity.UserImage;
import com.ogunkuade.images.repository.UserImageRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserImageRestService {

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
    public UserImageResponse imageRestUpload(byte[] image, Long id) throws IOException {
        //SAVING IMAGES TO DATABASE
        userImage = new UserImage();
        userImage.setUserId(id);
        userImage.setImage(image);
        UserImage savedImage = userImageRepository.save(userImage);
        userImageResponse = new UserImageResponse();
        userImageResponse.setId(savedImage.getId());
        userImageResponse.setUserId(savedImage.getUserId());
        userImageResponse.setImage(savedImage.getImage());
        return userImageResponse;
    }




    //GET SINGLE IMAGE
    public UserImageResponse getRestImage(Long id) throws IOException {
        userImage = userImageRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Image not found"));
        userImageResponse = new UserImageResponse();
        userImageResponse.setId(userImage.getId());
        userImageResponse.setUserId(userImage.getUserId());
        userImageResponse.setImage(userImage.getImage());
        return userImageResponse;
    }




    public UserImageResponse getRestImageByUserId(Long id) throws IOException {
        if(userImageRepository.existsByUserId(id)){
            userImage = userImageRepository.findImageByUserId(id);
            userImageResponse = new UserImageResponse();
            userImageResponse.setId(userImage.getId());
            userImageResponse.setUserId(userImage.getUserId());
            userImageResponse.setImage(userImage.getImage());
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
            userImageResponse.setUserId(userImage.getUserId());
            userImageResponse.setImage(userImage.getImage());
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
