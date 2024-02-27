package com.glamourjewellery.glamour_jewellery.controller;

import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.pojo.ProductPojo;
import com.glamourjewellery.glamour_jewellery.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/save")
    public String saveProduct(@RequestBody @ModelAttribute ProductPojo productPojo) throws IOException {
        productService.save(productPojo);
        return "Saved Successful";
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Product> getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) throws IOException {
        productService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @RequestBody ProductPojo productPojo) throws IOException {
        return productService.update(id, productPojo);
    }

    @GetMapping("/getByProductPrice/{productPrice}")
    public List<Product> getByProductPrice(@PathVariable("productPrice") double productPrice) {
        return productService.getByProductPrice(productPrice);
    }

    @GetMapping("/getByProductCategory/{productCategory}")
    public List<Product> getByProductCategory(@PathVariable("productCategory") String productCategory) {
        return productService.getByProductCategory(productCategory);
    }

    @GetMapping("/getByStockQuantity/{stockQuantity}")
    public List<Product> getByStockQuantity(@PathVariable("stockQuantity") Integer stockQuantity) {
        return productService.getByStockQuantity(stockQuantity);
    }

}
