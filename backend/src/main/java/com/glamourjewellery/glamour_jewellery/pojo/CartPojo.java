package com.glamourjewellery.glamour_jewellery.pojo;

import com.glamourjewellery.glamour_jewellery.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartPojo {
    private Long id;
    private List<Cart> cartItems;
    private Double cartTotal;
    private Long userId; // Assuming you need to associate the cart with a user
}
