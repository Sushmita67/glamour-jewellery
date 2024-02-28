package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.Cart;

public interface CartService {
    Cart createCart(Cart cart);
    Cart updateCart(Cart cart);
    Cart getCartById(Long id);
    void deleteCart(Long id);


}
