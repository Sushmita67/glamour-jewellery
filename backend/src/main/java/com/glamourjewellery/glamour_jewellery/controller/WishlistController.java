package com.glamourjewellery.glamour_jewellery.controller;

import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
import com.glamourjewellery.glamour_jewellery.entity.Wishlist;
import com.glamourjewellery.glamour_jewellery.pojo.WishlistPojo;
import com.glamourjewellery.glamour_jewellery.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/save")
    public String saveWishlist(@RequestBody WishlistPojo wishlistPojo) {
        return wishlistService.save(wishlistPojo);
    }

    @GetMapping("/getAll")
    public List<Wishlist> getAll() {
        return wishlistService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Wishlist getById(@PathVariable Long id) {
        return wishlistService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        wishlistService.deleteById(id);
    }

    @GetMapping("/getByUserAndProductId/{userId}/{productId}")
    public List<Wishlist> getByUserIdAndProductId(@PathVariable SystemUser userId, @PathVariable Product productId) {
        return wishlistService.getByUserIdAndProductId(userId, productId);
    }


    @GetMapping("/getByStatus/{status}")
    public List<Wishlist> getByStatus(@PathVariable Boolean status) {
        return wishlistService.getByStatus(status);
    }



}
