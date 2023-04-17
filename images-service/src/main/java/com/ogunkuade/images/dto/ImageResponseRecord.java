package com.ogunkuade.images.dto;


import com.ogunkuade.images.entity.UserImage;

public record ImageResponseRecord(String base64FormatOfImage, UserImage image) {
}
