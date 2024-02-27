package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.Cart;
import com.glamourjewellery.glamour_jewellery.entity.SystemUser;

public interface CartService {

    Cart createCart(SystemUser systemUser);

}
