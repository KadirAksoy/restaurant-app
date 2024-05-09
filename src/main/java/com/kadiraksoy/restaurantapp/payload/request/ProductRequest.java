package com.kadiraksoy.restaurantapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String name;
    private String description;
    private double price;
    private Long imageId;
    private CategoryRequest categoryRequest;
}
