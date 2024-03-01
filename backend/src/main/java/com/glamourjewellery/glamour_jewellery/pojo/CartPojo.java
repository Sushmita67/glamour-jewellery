package com.glamourjewellery.glamour_jewellery.pojo;

import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
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
    private Long cartId;
    private SystemUser user;
    private List<Product> products;
    private Double cartTotal;
}
