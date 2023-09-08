package com.hr.springdata.jpa;

import com.hr.springdata.jpa.onetooneunidirectional.entity.Product;
import com.hr.springdata.jpa.onetooneunidirectional.entity.UserManual;
import com.hr.springdata.jpa.onetooneunidirectional.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OneToOneUniDirectionalTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void addProductWithManual()
    {
        final UserManual userManual = UserManual.builder()
                .url("http://spring-course-123.com")
                .build();

        final Product product = Product.builder()
                .title("Gas Stove")
                .userManual(userManual)
                .build();


        productRepository.save(product);
    }

    @Test
    void getProducts()
    {
        final List<Product> userManuals = productRepository.findAll();
        System.out.println("Results : "+userManuals);
    }
}
