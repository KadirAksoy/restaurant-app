package com.kadiraksoy.restaurantapp.controller;

import com.kadiraksoy.restaurantapp.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @PostMapping("/addImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            var image = imageService.saveImageData(file);
            return ResponseEntity.ok(image.getId().toString());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }

    @PutMapping("/updateImage/{imageId}")
    public ResponseEntity<String> updateImage(@PathVariable Long imageId, @RequestParam("file") MultipartFile file) {
        try {
            var image = imageService.updateImageData(imageId, file);
            return ResponseEntity.ok(image.getId().toString());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error update image: " + e.getMessage());
        }
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long imageId) {
        byte[] imageData = imageService.getImageData(imageId);
        if (imageData != null) {
            return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(imageData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}