package com.glamourjewellery.glamour_jewellery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @SequenceGenerator(name = "carts_seq_gen", sequenceName = "carts_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "carts_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private SystemUser user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Column(name = "cart_total", nullable = false)
    private Double cartTotal;
}
