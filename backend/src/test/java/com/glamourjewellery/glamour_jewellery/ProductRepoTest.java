
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
        product.setProductType("Product Type");
        product.setProductItinerary("Day 1");
        product.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2024"));
        product.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2025"));
        product.setMaxParticipants(10);
        product.setProductRating(3L);
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

    @Test
    @Order(3)
    public void testDuration() throws ParseException {
        Date startDate=new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2024");
        Date endDate=new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2025");
        List<Product> productList = productRepo.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
        Assertions.assertThat(productList.size()).isGreaterThan(0);
    }

}

