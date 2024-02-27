package com.glamourjewellery.glamour_jewellery.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "cart_items"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @SequenceGenerator(name = "carts_seq_gen", sequenceName = "carts_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "carts_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "cart_item_id")
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Override
    public String toString() {
        return "Cart";
    }
    private int quantity;
}


