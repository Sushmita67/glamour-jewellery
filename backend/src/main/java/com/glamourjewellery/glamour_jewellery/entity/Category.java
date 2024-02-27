package com.glamourjewellery.glamour_jewellery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorys")
@Getter
@Setter
public class Category {

    @Id
    @SequenceGenerator(name = "categorys_seq_gen", sequenceName = "categorys_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "categorys_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = true)
    private String image;

}
