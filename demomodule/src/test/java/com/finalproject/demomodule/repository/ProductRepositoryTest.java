package com.finalproject.demomodule.repository;

import com.finalproject.demomodule.entity.unidirection.Product;
import com.finalproject.demomodule.repository.unidirection.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod() {
        // create product
        int i = 0;
        List<Product> products = new ArrayList<>();
        while (i < 50) {

            Product product = new Product();
            product.setName("product " + i);
            product.setDescription("product " + i + " description");
            product.setSku(i + "ABC");
            product.setPrice(new BigDecimal(i));
            product.setActive(true);
            product.setImageUrl("product" + i + ".png");
            products.add(product);
            i++;
        }
        productRepository.saveAll(products);

    }

    @Test
    void updateUsingSaveMethod() {

        // find or retrieve an entity by id
        Long id = 1L;
        Product product = productRepository.findById(id).get();

        // update entity information
        product.setName("updated product 1");
        product.setDescription("updated product 1 desc");

        // save updated entity
        productRepository.save(product);
    }

    @Test
    void findByIdMethod() {
        Long id = 1L;

        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod() {

        // create product
        Product product = new Product();
        product.setName("product 2");
        product.setDescription("product 2 description");
        product.setSku("100ABCD");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageUrl("product2.png");

        // create product
        Product product3 = new Product();
        product3.setName("product 3");
        product3.setDescription("product 3 description");
        product3.setSku("100ABCDE");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product3.png");

        productRepository.saveAll(List.of(product, product3));

    }

    @Test
    void findAllMethod() {

        List<Product> products = productRepository.findAll();

        products.forEach((p) -> {
            System.out.println(p.getName());
        });

    }

    @Test
    void deleteByIdMethod() {
        Long id = 1L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod() {

        // find an entity by id
        Long id = 2L;
        Product product = productRepository.findById(id).get();

        // delete(entity)
        productRepository.delete(product);

    }

    @Test
    void deleteAllMethod() {

        // productRepository.deleteAll();

        Product product = productRepository.findById(5L).get();

        Product product1 = productRepository.findById(6L).get();

        productRepository.deleteAll(List.of(product, product1));

    }

    @Test
    void existsByIdMethod() {
        Long id = 7L;

        boolean result = productRepository.existsById(id);

        System.out.println(result);
    }

    @Test
    void countMethod() {
        long count = productRepository.count();
        System.out.println(count);
    }

}