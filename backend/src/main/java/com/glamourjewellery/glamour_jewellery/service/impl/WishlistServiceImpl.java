package com.glamourjewellery.glamour_jewellery.service.impl;

import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
import com.glamourjewellery.glamour_jewellery.entity.Wishlist;
import com.glamourjewellery.glamour_jewellery.pojo.WishlistPojo;
import com.glamourjewellery.glamour_jewellery.repo.ProductRepo;
import com.glamourjewellery.glamour_jewellery.repo.SystemUserRepo;
import com.glamourjewellery.glamour_jewellery.repo.WishlistRepo;
import com.glamourjewellery.glamour_jewellery.service.WishlistService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepo wishlistRepo;
    private final SystemUserRepo systemUserRepo;
    private final ProductRepo productRepo;

    @Override
    public String save(WishlistPojo wishlistPojo) {
        Wishlist wishlist = new Wishlist();

        // Set wishlistId if available
        Long wishlistId = wishlistPojo.getId();
        if (wishlistId != null) {
            wishlist.setId(wishlistId);
        }

        // Fetch the Product from the repository using the provided productId
        Long productId = wishlistPojo.getProductId();
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
        wishlist.setProductId(product);

        // Fetch the SystemUser from the repository using the provided userId
        Long userId = wishlistPojo.getUserId();
        SystemUser user = systemUserRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("SystemUser not found with ID: " + userId));
        wishlist.setUserId(user);

        // Set other properties
        wishlist.setStatus(wishlistPojo.getStatus());


        // Save the wishlist
        wishlistRepo.save(wishlist);

        return "Saved Successfully!";
    }

    @Override
    public List<Wishlist> getAll() {
        return wishlistRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        wishlistRepo.deleteById(id);
    }

    @Override
    public Wishlist getById(Long id) {
        return wishlistRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist not found with ID: " + id));
    }

    @Override
    public List<Wishlist> getByUserIdAndProductId(SystemUser userId, Product productId) {
        return wishlistRepo.findByUserIdAndProductId(userId, productId);
    }



    @Override
    public List<Wishlist> getByStatus(Boolean status) {
        return wishlistRepo.findByStatus(status);
    }
}
