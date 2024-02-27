package com.glamourjewellery.glamour_jewellery.service.impl;

import com.glamourjewellery.glamour_jewellery.entity.Cart;
import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
import com.glamourjewellery.glamour_jewellery.repo.CartRepo;
import com.glamourjewellery.glamour_jewellery.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;

    @Override
    public Cart createCart(SystemUser systemUser) {
        Cart newCart = Cart.builder()
                .systemUser(systemUser)
                .totalPrice(0.0)
                .build();
        return cartRepo.save(newCart);
    }
}
