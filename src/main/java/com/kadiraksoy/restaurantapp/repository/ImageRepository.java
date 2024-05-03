package com.kadiraksoy.restaurantapp.repository;

import com.kadiraksoy.restaurantapp.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageData, Long> {
}
