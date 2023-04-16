package com.ogunkuade.images.service;


import com.ogunkuade.images.dto.UserImageResponse;
import com.ogunkuade.images.entity.UserImage;
import com.ogunkuade.images.repository.UserImageRepository;
import org.springframework.stereotype.Service;
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
    public UserImageResponse imageUploadRestComplete(Long id, MultipartFile my_photo) throws IOException {
        //SAVING IMAGES TO DATABASE
        userImage = new UserImage();
        userImage.setName(my_photo.getOriginalFilename());
        userImage.setType(my_photo.getContentType());
        userImage.setUserId(id);
        userImage.setImage(my_photo.getBytes());
        UserImage savedImage = userImageRepository.save(userImage);

        userImageResponse = new UserImageResponse();
        userImageResponse.setId(savedImage.getId());
        userImageResponse.setName(savedImage.getName());
        userImageResponse.setType(savedImage.getType());
        userImageResponse.setUserId(savedImage.getUserId());
        userImageResponse.setImage(savedImage.getImage());

        //SAVING IMAGE TO LOCAL DISK
        imageIDStr = id.toString();
        //DEFINE AND CREATE UPLOAD_PATH
        UPLOAD_PATH = Path.of(IMAGE_PATH + File.separator + imageIDStr);
        if(Files.notExists(UPLOAD_PATH)){
            Files.createDirectory(UPLOAD_PATH);
        }
        //DEFINE AND CREATE A LOCAL IMAGE
        localImage = Path.of(UPLOAD_PATH + File.separator + my_photo.getOriginalFilename());
        if(Files.exists(localImage)){
            createLocalImage = Files.write(localImage, my_photo.getBytes());
            nameOfCreatedLocalImage = createLocalImage.getFileName().toString();
        } else{
            Path emptyLocalImage = Files.createFile(localImage);
            createLocalImage = Files.write(emptyLocalImage, my_photo.getBytes());
            nameOfCreatedLocalImage = createLocalImage.getFileName().toString();
        }

        return userImageResponse;
    }




    //GET SINGLE IMAGE
    public UserImageResponse getRestImage(Long id) throws IOException {
        //RETRIEVING IMAGE FROM DATABASE
        if(userImageRepository.existsById(id)){
            savedUserImage = userImageRepository.findById(id).get();
            userImageResponse = new UserImageResponse();
            userImageResponse.setId(savedUserImage.getId());
            userImageResponse.setName(savedUserImage.getName());
            userImageResponse.setType(savedUserImage.getType());
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
            userImageResponse.setName(userImage.getName());
            userImageResponse.setType(userImage.getType());
            userImageResponse.setUserId(userImage.getUserId());
            userImageResponse.setImage(userImage.getImage());
            userImageResponseList.add(userImageResponse);
        }
        return userImageResponseList;
    }









}
