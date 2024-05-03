package com.kadiraksoy.restaurantapp.payload.response;


import com.kadiraksoy.restaurantapp.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {


    private String name;
    private String description;
    private double price;
    private Long imageId;
    private Category category;

}
