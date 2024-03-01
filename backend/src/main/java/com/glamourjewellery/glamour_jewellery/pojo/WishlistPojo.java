package com.glamourjewellery.glamour_jewellery.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistPojo {

    private Long id;

    private Long userId;

    private Long productId;

    private Boolean status;

}
