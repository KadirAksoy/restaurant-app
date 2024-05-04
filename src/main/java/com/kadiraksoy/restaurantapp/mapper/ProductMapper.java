package com.kadiraksoy.restaurantapp.mapper;


import com.kadiraksoy.restaurantapp.model.Category;
import com.kadiraksoy.restaurantapp.model.Product;
import com.kadiraksoy.restaurantapp.payload.request.CategoryRequest;
import com.kadiraksoy.restaurantapp.payload.request.ProductRequest;
import com.kadiraksoy.restaurantapp.payload.response.CategoryResponse;
import com.kadiraksoy.restaurantapp.payload.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public Product mapProductRequestToEntity(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .category(Category.builder()
                        .id(productRequest.getCategoryRequest().getId())
                        .build())
                .description(productRequest.getDescription())
                .imageId(productRequest.getImageId())
                .build();
    }
    public ProductResponse mapEntityToProductResponse(Product product,Category categoryResponse){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .categoryResponse(CategoryResponse.builder()
                        .id(product.getCategory().getId())
                        .name(categoryResponse.getName())
                        .build())
                .description(product.getDescription())
                .imageId(product.getImageId())
                .build();
    }

    public ProductResponse mapProductRequestToProductResponse(
            ProductRequest productRequest,
            CategoryResponse categoryResponse){
        return ProductResponse.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .categoryResponse(CategoryResponse.builder()
                        .id(categoryResponse.getId())
                        .name(categoryResponse.getName()).build())
                .description(productRequest.getDescription())
                .imageId(productRequest.getImageId())
                .build();
    }
}
