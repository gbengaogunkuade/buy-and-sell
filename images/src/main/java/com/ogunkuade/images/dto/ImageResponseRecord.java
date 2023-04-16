package com.ogunkuade.images.dto;


import com.ogunkuade.images.entity.Image;



public record ImageResponseRecord(String base64FormatOfImage, Image image) {
}
