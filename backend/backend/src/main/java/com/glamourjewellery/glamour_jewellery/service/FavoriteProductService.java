package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.FavoriteProduct;

import java.util.List;

public interface FavoriteProductService {
    List<FavoriteProduct> getFavoriteProducts(Long userId);
    void addToFavorites(Long userId, Long productId);
    void removeFromFavorites(Long userId, Long productId);
}
