package com.kadiraksoy.restaurantapp.repository;

import com.kadiraksoy.restaurantapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
