package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.Category;
import com.glamourjewellery.glamour_jewellery.pojo.CategoryPojo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    String save(CategoryPojo categoryPojo) throws IOException;

    List<Category> getAll();

    void deleteById(Long id) throws IOException;

    Optional<Category> getById(Long id);

    String update(Long id, CategoryPojo categoryPojo) throws IOException;

}
