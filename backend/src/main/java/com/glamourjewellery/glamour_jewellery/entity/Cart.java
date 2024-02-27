package com.glamourjewellery.glamour_jewellery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "carts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    @SequenceGenerator(name = "carts_seq_gen", sequenceName = "carts_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "carts_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "cart_id")
    private Long cartId;



    private Double totalPrice;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )

    private SystemUser systemUser;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<CartItem> cartItems;

}
