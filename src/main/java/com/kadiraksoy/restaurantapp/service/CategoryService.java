package com.kadiraksoy.restaurantapp.service;

import com.kadiraksoy.restaurantapp.exception.CategoryNotFound;
import com.kadiraksoy.restaurantapp.model.Category;
import com.kadiraksoy.restaurantapp.payload.request.CategoryRequest;
import com.kadiraksoy.restaurantapp.payload.response.CategoryResponse;
import com.kadiraksoy.restaurantapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest categoryRequest){
        Category category = Category.builder().name(categoryRequest.getName()).build();
        categoryRepository.save(category);
        log.info("category created.");
        return CategoryResponse.builder().name(categoryRequest.getName()).build();
    }
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category not found."));

        category.setName(category.getName());
        log.info("category updated.");
        return CategoryResponse.builder().name(categoryRequest.getName()).build();
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
        log.info("category deleted.");
    }
    public List<CategoryResponse> getAllCategory(){
        List<Category> categoryList = categoryRepository.findAll();
        log.info("All categories listed.");
        return categoryList.stream()
                .map(category -> new CategoryResponse(category.getName()))
                .collect(Collectors.toList());
    }
    public CategoryResponse getCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category not found"));
        log.info("category found.");
        return CategoryResponse.builder().name(category.getName()).build();
    }
}
