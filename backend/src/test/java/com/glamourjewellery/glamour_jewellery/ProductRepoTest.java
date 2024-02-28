
package com.glamourjewellery.glamour_jewellery;


import com.glamourjewellery.glamour_jewellery.entity.Product;
import com.glamourjewellery.glamour_jewellery.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void save() throws ParseException {
        Product product = new Product();

        product.setProductName("Product Name For Test");
        product.setProductDescription("Product Description");
        product.setProductCategory("Category");
        product.setStockQuantity(10);
        product.setProductPrice(1200.1);
        product.setProductAvailability(true);

        product=productRepo.save(product);

        Assertions.assertThat(product.getProductId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public  void getById(){
        Product product= productRepo.findById(1L).get();
        Assertions.assertThat(product.getProductId()).isEqualTo(1L);
    }


}

