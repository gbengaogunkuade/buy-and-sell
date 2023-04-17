package com.ogunkuade.images.service;


import com.ogunkuade.images.dto.UserImageResponse;
import com.ogunkuade.images.entity.UserImage;
import com.ogunkuade.images.repository.UserImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
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
        UserImage savedImage = userImageRepository.save(userImage);

        userImageResponse = new UserImageResponse();
        userImageResponse.setId(savedImage.getId());
        userImageResponse.setUserId(savedImage.getUserId());
        userImageResponse.setImage(savedImage.getImage());

        return userImageResponse;
    }




    //GET SINGLE IMAGE
    public UserImageResponse getRestImage(Long id) throws IOException {
        //RETRIEVING IMAGE FROM DATABASE
        if(userImageRepository.existsById(id)){
            savedUserImage = userImageRepository.findById(id).get();
            userImageResponse = new UserImageResponse();
            userImageResponse.setId(savedUserImage.getId());
            userImageResponse.setUserId(savedUserImage.getUserId());
            userImageResponse.setImage(savedUserImage.getImage());
            return userImageResponse;
        } else{
            throw new FileNotFoundException("Image not found");
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









}
