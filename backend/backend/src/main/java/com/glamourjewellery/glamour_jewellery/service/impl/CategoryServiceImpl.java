package com.glamourjewellery.glamour_jewellery.service.impl;

import com.glamourjewellery.glamour_jewellery.entity.Category;
import com.glamourjewellery.glamour_jewellery.pojo.CategoryPojo;
import com.glamourjewellery.glamour_jewellery.repo.CategoryRepo;
import com.glamourjewellery.glamour_jewellery.service.CategoryService;
import com.glamourjewellery.glamour_jewellery.utils.ImageToBase64;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    ImageToBase64 imageToBase64=new ImageToBase64();

    @Override
    public String save(CategoryPojo categoryPojo) throws IOException {
        Category category = new Category();
        category.setDescription(categoryPojo.getDescription());

        if (categoryPojo.getImage() != null) {
            String fileName = saveImage(categoryPojo.getImage());
            category.setImage(fileName);
        }

        categoryRepo.save(category);
        return "Saved Successfully!";
    }

    private String saveImage(MultipartFile image) throws IOException {
        String uploadDir = "image_uploads";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = image.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(image.getInputStream(), filePath);
        return fileName;
    }

    @Override
    public List<Category> getAll() {


        return categoryRepo.findAll().stream().map(item -> {
            item.setImage(imageToBase64.getImageBase64(item.getImage()));
            return item;
        }).collect(Collectors.toList());


    }

    @Override
    public void deleteById(Long id) throws IOException {
        Category category= categoryRepo.findById(id).get();
        String uploadDir = "image_uploads/"+category.getImage();
        Path uploadPath = Paths.get(uploadDir);
        Files.deleteIfExists(uploadPath);

        categoryRepo.deleteById(id);
    }


    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public String update(Long id, CategoryPojo categoryPojo) throws IOException {
        Category existingCategory = categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + id));

        existingCategory.setDescription(categoryPojo.getDescription());

        if (categoryPojo.getImage() != null) {
            String fileName = saveImage(categoryPojo.getImage());
            existingCategory.setImage(fileName);
        }

        categoryRepo.save(existingCategory);
        return "Updated Successfully!";
    }
}
