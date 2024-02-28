package com.glamourjewellery.glamour_jewellery.repo;

import com.glamourjewellery.glamour_jewellery.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    // You can add custom queries if needed
}
