package com.glamourjewellery.glamour_jewellery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Wishlist_Seq_GEN")
    @SequenceGenerator(name = "Wishlist_Seq_GEN", sequenceName = "Wishlist_Seq", initialValue = 1,
            allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Ensure the column name matches the primary key of SystemUser
    @JsonIgnore
    private SystemUser userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false) // Ensure the column name matches the primary key of Product
    @JsonIgnore
    private Product productId;

    @Column(name = "status", nullable = false)
    private Boolean status;


}
