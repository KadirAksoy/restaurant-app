package com.kadiraksoy.restaurantapp.service;

import com.kadiraksoy.restaurantapp.exception.ImageDataNotFoundException;
import com.kadiraksoy.restaurantapp.model.ImageData;
import com.kadiraksoy.restaurantapp.repository.ImageRepository;
import com.kadiraksoy.restaurantapp.util.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@AllArgsConstructor
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public ImageData saveImageData(MultipartFile file) throws IOException {
        byte [] image = ImageUtils.compressImage(file.getBytes());
        ImageData newImageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(image)
                .build();
        ImageData imageData = imageRepository.save(newImageData);
        log.info("image saved.");
        return imageData;
    }


    @Transactional
    public ImageData updateImageData(Long id, MultipartFile file) throws IOException {
        ImageData imageDataToUpdate = imageRepository.findById(id)
                .orElseThrow(() -> new ImageDataNotFoundException("Image data not found with id: " + id));

        byte[] compressedImage = ImageUtils.compressImage(file.getBytes());

        imageDataToUpdate.setName(file.getOriginalFilename());
        imageDataToUpdate.setType(file.getContentType());
        imageDataToUpdate.setImageData(compressedImage);

        ImageData updatedImage = imageRepository.save(imageDataToUpdate);
        log.info("image updated.");
        return updatedImage;
    }

    public void deleteImageData(Long id) {
        imageRepository.deleteById(id);
        log.info("image deleted with id:" + id);
    }

    public byte[] getImageData(Long id) {
        var compressedImage = imageRepository.findById(id);
        return ImageUtils.decompressImage(compressedImage.orElseThrow().getImageData());
    }
}