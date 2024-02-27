package com.glamourjewellery.glamour_jewellery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {

    @Id
    @SequenceGenerator(name = "products_seq_gen", sequenceName = "products_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "products_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Column(name = "category", nullable = false)
    private String productCategory;

    @Column(name = "description", nullable = true)
    private String productDescription;

    private String image;

    @Column(name = "stock_qty", nullable = true)
    private Integer stockQuantity;

    @Column(name = "availability", nullable = true)
    private Boolean productAvailability;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItems;

    @Override
    public String toString() {
        return "Product Name: " + getProductName() + ", Price: " + getProductPrice();
    }

}
