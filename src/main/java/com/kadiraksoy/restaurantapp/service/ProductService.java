package com.kadiraksoy.restaurantapp.service;

import com.kadiraksoy.restaurantapp.exception.ProductNotFoundException;
import com.kadiraksoy.restaurantapp.mapper.CategoryMapper;
import com.kadiraksoy.restaurantapp.mapper.ProductMapper;
import com.kadiraksoy.restaurantapp.model.Category;
import com.kadiraksoy.restaurantapp.model.Product;
import com.kadiraksoy.restaurantapp.payload.request.ProductRequest;
import com.kadiraksoy.restaurantapp.payload.response.CategoryResponse;
import com.kadiraksoy.restaurantapp.payload.response.ProductResponse;
import com.kadiraksoy.restaurantapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = productMapper.mapProductRequestToEntity(productRequest);
        productRepository.save(product);
        log.info("product created.");

        CategoryResponse category = categoryService.getCategoryById(productRequest.getCategoryRequest().getId());

        return productMapper.mapProductRequestToProductResponse(productRequest,category);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        product.setCategory(Category.builder()
                .id(productRequest.getCategoryRequest().getId())
                .build());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageId(product.getImageId());

        productRepository.save(product);
        log.info("product updated.");

        CategoryResponse category = categoryService.getCategoryById(product.getCategory().getId());

        return productMapper.mapProductRequestToProductResponse(productRequest,category);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
        log.info("product deleted with id: " + id);
    }

    public ProductResponse getProductById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            Category category = categoryMapper
                    .mapCategoryResponseToEntity(
                            categoryService.getCategoryById(product.getCategory().getId())
                    );

            return productMapper.mapEntityToProductResponse(product,category);
        } else {
            throw new ProductNotFoundException("Ürün bulunamadı: " + id);
        }
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product ->
                        productMapper
                                .mapEntityToProductResponse(product,product.getCategory()))
                .collect(Collectors.toList());
    }


}
