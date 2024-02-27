package com.glamourjewellery.glamour_jewellery.pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPojo {

    private Long productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Product price is required")
    @Positive(message = "Product price must be positive")
    private Double productPrice;

    @NotBlank(message = "Product category is required")
    private String productCategory;

    private String productDescription;

    private MultipartFile image;

    private Integer stockQuantity;

    private Boolean productAvailability;
}
