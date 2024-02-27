package com.glamourjewellery.glamour_jewellery.service.impl;

import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.pojo.ProductPojo;
import com.glamourjewellery.glamour_jewellery.repo.ProductRepo;
import com.glamourjewellery.glamour_jewellery.service.ProductService;
import com.glamourjewellery.glamour_jewellery.utils.ImageToBase64;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ImageToBase64 imageToBase64 = new ImageToBase64();

    @Override
    public String save(ProductPojo productPojo) throws IOException {
        Product product;

        if (productPojo.getProductId() != null) {
            product = productRepo.findById(productPojo.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productPojo.getProductId()));
        } else {
            product = new Product();
        }

        product.setProductName(productPojo.getProductName());
        product.setProductDescription(productPojo.getProductDescription());
        product.setProductCategory(productPojo.getProductCategory()); // Updated from getProductType to getProductCategory
        product.setProductPrice(productPojo.getProductPrice());
        product.setStockQuantity(productPojo.getStockQuantity());
        product.setProductAvailability(true);

        MultipartFile imageFile = productPojo.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            Path fileNameAndPath = Paths.get("image_uploads", imageFile.getOriginalFilename());
            Files.write(fileNameAndPath, imageFile.getBytes());
            product.setImage(imageFile.getOriginalFilename());
        }

        productRepo.save(product);
        return "Saved Successfully!";
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(product -> {
            product.setImage(imageToBase64.getImageBase64(product.getImage()));
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) throws IOException {
        Product product= productRepo.findById(id).get();
        String uploadDir = "image_uploads/"+product.getImage();
        Path uploadPath = Paths.get(uploadDir);
        Files.deleteIfExists(uploadPath);
        productRepo.deleteById(id);
    }

    @Override
    public Optional<Product> getById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        productOptional.ifPresent(product -> product.setImage(imageToBase64.getImageBase64(product.getImage())));
        return productOptional;
    }

    @Override
    public String update(Long id, ProductPojo productPojo) throws IOException {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));

        existingProduct.setProductName(productPojo.getProductName());
        existingProduct.setProductDescription(productPojo.getProductDescription());
        existingProduct.setProductCategory(productPojo.getProductCategory());
        existingProduct.setProductPrice(productPojo.getProductPrice());
        existingProduct.setStockQuantity(productPojo.getStockQuantity());
        existingProduct.setProductAvailability(true);

        if (productPojo.getImage() != null) {
            Path fileNameAndPath = Paths.get("image_uploads", productPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, productPojo.getImage().getBytes());
            existingProduct.setImage(productPojo.getImage().getOriginalFilename());
        }

        productRepo.save(existingProduct);
        return "Updated Successfully!";
    }

    @Override
    public List<Product> getByProductPrice(double productPrice) {
        return productRepo.findByProductPrice(productPrice);
    }

    @Override
    public List<Product> getByProductCategory(String productCategory) {
        return productRepo.findByProductCategory(productCategory);
    }

    @Override
    public List<Product> getByStockQuantity(Integer stockQuantity) {
        return productRepo.findByStockQuantity(stockQuantity);
    }
}

