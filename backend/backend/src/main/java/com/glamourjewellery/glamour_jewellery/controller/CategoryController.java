package com.glamourjewellery.glamour_jewellery.controller;

import com.glamourjewellery.glamour_jewellery.entity.Category;
import com.glamourjewellery.glamour_jewellery.pojo.CategoryPojo;
import com.glamourjewellery.glamour_jewellery.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/save")
    public String saveCategory(@RequestBody @ModelAttribute CategoryPojo categoryPojo) throws IOException {
        return categoryService.save(categoryPojo);
    }

    @GetMapping("/getAll")
    public List<Category> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Category> getById(@PathVariable("id") Long id) {
        return this.categoryService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) throws IOException {
        this.categoryService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, @ModelAttribute CategoryPojo categoryPojo) throws IOException {
        return this.categoryService.update(id, categoryPojo);
    }
}
