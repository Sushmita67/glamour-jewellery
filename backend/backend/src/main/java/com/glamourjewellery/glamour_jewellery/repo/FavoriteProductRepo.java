package com.glamourjewellery.glamour_jewellery.repo;

import com.glamourjewellery.glamour_jewellery.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteProductRepo extends JpaRepository<FavoriteProduct, Long> {
    List<FavoriteProduct> findByUserId(Long userId);
}
