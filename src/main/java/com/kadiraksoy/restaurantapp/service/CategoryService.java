package com.kadiraksoy.restaurantapp.service;

import com.kadiraksoy.restaurantapp.exception.CategoryNotFoundException;
import com.kadiraksoy.restaurantapp.mapper.CategoryMapper;
import com.kadiraksoy.restaurantapp.model.Category;
import com.kadiraksoy.restaurantapp.payload.request.CategoryServiceRequest;
import com.kadiraksoy.restaurantapp.payload.response.CategoryResponse;
import com.kadiraksoy.restaurantapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryServiceRequest categoryRequest){
        Category category = Category.builder()
                .name(categoryRequest.getName()).build();
        categoryRepository.save(category);
        log.info("category created.");

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }
    public CategoryResponse updateCategory(Long id, CategoryServiceRequest categoryRequest){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found."));

        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        log.info("category updated.");

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
        log.info("category deleted.");
    }
    public List<CategoryResponse> getAllCategory(){
        List<Category> categoryList = categoryRepository.findAll();
        log.info("All categories listed.");
        return categoryList.stream()
                .map(categoryMapper::mapCategoryToCategoryResponse)
                .collect(Collectors.toList());
    }
    public CategoryResponse getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        log.info("category found.");
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }
}
