package com.glamourjewellery.glamour_jewellery.service.impl;

import com.glamourjewellery.glamour_jewellery.entity.FavoriteProduct;
import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
import com.glamourjewellery.glamour_jewellery.repo.FavoriteProductRepo;
import com.glamourjewellery.glamour_jewellery.repo.ProductRepo;
import com.glamourjewellery.glamour_jewellery.repo.SystemUserRepo;
import com.glamourjewellery.glamour_jewellery.service.FavoriteProductService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteProductServiceImpl implements FavoriteProductService {

    private final FavoriteProductRepo favoriteProductRepo;
    private final ProductRepo productRepo;



    public FavoriteProductServiceImpl(FavoriteProductRepo favoriteProductRepo, ProductRepo productRepo, SystemUserRepo systemUserRepo) {
        this.favoriteProductRepo = favoriteProductRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<FavoriteProduct> getFavoriteProducts(Long userId) {
        return favoriteProductRepo.findByUserId(userId);
    }

    @Override
    public void addToFavorites(Long userId, Long productId) {
////        // Fetch user and product entities
////        SystemUser systemUser = systemUserRepo.findById(userId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
////        Product product = productRepo.findById(productId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
////
////        // Create a new favorite product entry
////        FavoriteProduct favoriteProduct = new FavoriteProduct();
////        favoriteProduct.setSystemUser(systemUser);
////        favoriteProduct.setProduct(product);
////
////        // Save the favorite product
//        favoriteProductRepo.save(favoriteProduct);
    }

    @Override
    public void removeFromFavorites(Long userId, Long productId) {
//        // Fetch favorite product entry
//        FavoriteProduct favoriteProduct = favoriteProductRepo.findByUserIdAndProductId(userId, productId)
//                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Favorite product not found"));
//
//        // Remove the favorite product
//        favoriteProductRepo.delete(favoriteProduct);
    }
}
