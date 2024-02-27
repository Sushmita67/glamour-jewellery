package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.pojo.ProductPojo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    String save(ProductPojo productPojo) throws IOException;

    List<Product> getAll();

    void deleteById(Long id) throws IOException;

    Optional<Product> getById(Long id);

    String update(Long id, ProductPojo productPojo) throws IOException;

    List<Product> getByProductPrice(double productPrice);

    List<Product> getByProductCategory(String productCategory); // Change from getByProductType to getByProductCategory

    List<Product> getByStockQuantity(Integer stockQuantity); // New method

}
