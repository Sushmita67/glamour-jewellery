package com.glamourjewellery.glamour_jewellery.repo;

import com.glamourjewellery.glamour_jewellery.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByProductPrice(double productPrice);

    List<Product> findByProductCategory(String productCategory);

    List<Product> findByStockQuantity(Integer stockQuantity);
}
