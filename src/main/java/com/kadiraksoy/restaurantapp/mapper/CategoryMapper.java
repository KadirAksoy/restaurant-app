package com.kadiraksoy.restaurantapp.mapper;

import com.kadiraksoy.restaurantapp.model.Category;
import com.kadiraksoy.restaurantapp.payload.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapCategoryResponseToEntity(CategoryResponse categoryResponse){
        return Category.builder()
                .id(categoryResponse.getId())
                .name(categoryResponse.getName())
                .build();
    }

    public CategoryResponse mapCategoryToCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName()).build();
    }
}
