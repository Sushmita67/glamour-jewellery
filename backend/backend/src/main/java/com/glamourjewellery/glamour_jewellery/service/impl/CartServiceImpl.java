package com.glamourjewellery.glamour_jewellery.service.impl;

import com.glamourjewellery.glamour_jewellery.entity.Cart;
import com.glamourjewellery.glamour_jewellery.repo.CartRepo;
import com.glamourjewellery.glamour_jewellery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    @Autowired
    public CartServiceImpl(CartRepo cartRepository) {
        this.cartRepo = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        // Implement your update logic here
        return null;
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepo.deleteById(id);
    }

}
