package com.kadiraksoy.restaurantapp.repository;


import com.kadiraksoy.restaurantapp.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageData, Long> {
}
