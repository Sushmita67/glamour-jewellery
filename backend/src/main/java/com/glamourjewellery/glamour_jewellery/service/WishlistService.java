package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
import com.glamourjewellery.glamour_jewellery.entity.Wishlist;
import com.glamourjewellery.glamour_jewellery.pojo.WishlistPojo;

import java.util.List;

public interface WishlistService {

    String save(WishlistPojo wishlistPojo);

    List<Wishlist> getAll();

    void deleteById(Long id);

    Wishlist getById(Long id);

    List<Wishlist> getByUserIdAndProductId(SystemUser userId, Product productId);

    List<Wishlist> getByStatus(Boolean status);

}
