package com.kadiraksoy.restaurantapp.service;

import com.kadiraksoy.restaurantapp.exception.ProductNotFoundException;
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

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .category(Category.builder()
                        .id(productRequest.getCategoryRequest().getId())
                        .build())
                .description(productRequest.getDescription())
                .imageId(productRequest.getImageId())
                .build();

        productRepository.save(product);
        log.info("product created.");

        CategoryResponse category = categoryService.getCategoryById(productRequest.getCategoryRequest().getId());

        return ProductResponse.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .categoryResponse(CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName()).build())
                .description(productRequest.getDescription())
                .imageId(productRequest.getImageId())
                .build();
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

        return ProductResponse.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .categoryResponse(CategoryResponse.builder()
                        .id(productRequest.getCategoryRequest().getId())
                        .build())
                .description(productRequest.getDescription())
                .imageId(productRequest.getImageId())
                .build();
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
        log.info("product deleted with id: " + id);
    }

    public ProductResponse getProductById(Long id){
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            CategoryResponse category = categoryService.getCategoryById(product.getCategory().getId());

            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .categoryResponse(CategoryResponse.builder()
                            .id(product.getCategory().getId())
                            .name(category.getName())
                            .build())
                    .description(product.getDescription())
                    .imageId(product.getImageId())
                    .build();
        } else {
            throw new ProductNotFoundException("Ürün bulunamadı: " + id);
        }
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .categoryResponse(CategoryResponse.builder()
                                .id(product.getCategory().getId())
                                .name(product.getCategory().getName())
                                .build())
                        .description(product.getDescription())
                        .imageId(product.getImageId())
                        .build())
                .collect(Collectors.toList());
    }


}
