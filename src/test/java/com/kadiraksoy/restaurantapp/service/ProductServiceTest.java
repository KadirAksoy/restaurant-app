package com.kadiraksoy.restaurantapp.service;

import com.kadiraksoy.restaurantapp.exception.ProductNotFoundException;
import com.kadiraksoy.restaurantapp.mapper.CategoryMapper;
import com.kadiraksoy.restaurantapp.mapper.ProductMapper;
import com.kadiraksoy.restaurantapp.model.Category;
import com.kadiraksoy.restaurantapp.model.Product;
import com.kadiraksoy.restaurantapp.payload.request.CategoryRequest;
import com.kadiraksoy.restaurantapp.payload.request.ProductRequest;
import com.kadiraksoy.restaurantapp.payload.response.CategoryResponse;
import com.kadiraksoy.restaurantapp.payload.response.ProductResponse;
import com.kadiraksoy.restaurantapp.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;
    @Mock
    private ProductMapper productMapper;

    @Mock
    private CategoryMapper categoryMapper;


    @InjectMocks
    private ProductService productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {

        CategoryRequest categoryRequest = new CategoryRequest(1L);

        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Test Product");
        productRequest.setDescription("Test Description");
        productRequest.setPrice(10.0);
        productRequest.setImageId(1L);
        productRequest.setCategoryRequest(categoryRequest);

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(1L);
        categoryResponse.setName("Test Category");

        when(categoryService.getCategoryById(anyLong())).thenReturn(categoryResponse);

        ProductResponse productResponse = productService.createProduct(productRequest);

        assertEquals(productRequest.getName(), productResponse.getName());
        assertEquals(productRequest.getDescription(), productResponse.getDescription());
        assertEquals(productRequest.getPrice(), productResponse.getPrice());
        assertEquals(productRequest.getImageId(),productResponse.getImageId());


    }
    @Test
    void testGetProductById_ProductNotFound() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));
    }

}