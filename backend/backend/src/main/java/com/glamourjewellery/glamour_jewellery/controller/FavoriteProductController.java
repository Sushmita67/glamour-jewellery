//package com.glamourjewellery.glamour_jewellery.controller;
//
//import com.glamourjewellery.glamour_jewellery.entity.FavoriteProduct;
//import com.glamourjewellery.glamour_jewellery.service.FavoriteProductService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/user/favorite")
//public class FavoriteProductController {
//
//    private final FavoriteProductService favoriteProductService;
//
//    public FavoriteProductController(FavoriteProductService favoriteProductService) {
//        this.favoriteProductService = favoriteProductService;
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<FavoriteProduct>> getFavoriteProducts(@PathVariable Long userId) {
//        List<FavoriteProduct> favoriteProducts = favoriteProductService.getFavoriteProducts(userId);
//        return ResponseEntity.ok(favoriteProducts);
//    }
//
//    @PostMapping("/{userId}/{productId}")
//    public ResponseEntity<?> addToFavorites(@PathVariable Long userId, @PathVariable Long productId) {
//        favoriteProductService.addToFavorites(userId, productId);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{userId}/{productId}")
//    public ResponseEntity<?> removeFromFavorites(@PathVariable Long userId, @PathVariable Long productId) {
//        favoriteProductService.removeFromFavorites(userId, productId);
//        return ResponseEntity.ok().build();
//    }
//}
