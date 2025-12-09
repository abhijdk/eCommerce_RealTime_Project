package com.eCommerce.repository;

import com.eCommerce.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductName(String productName);

    @Query("SELECT o FROM Product o WHERE o.category = :category")
    List<Product> getProductsByCategory(@Param("category") String category);

    List<Product> findAllByProductNameContainingIgnoreCase(String productName, Sort sortOrder);
}
