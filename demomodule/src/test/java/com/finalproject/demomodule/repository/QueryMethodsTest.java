package com.finalproject.demomodule.repository;

import com.finalproject.demomodule.entity.unidirection.Product;
import com.finalproject.demomodule.repository.unidirection.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {

        Product product = productRepository.findByName("product 2");

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByIdMethod() {
        Product product = productRepository.findById(1L).get();

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByNameOrDescriptionMethod() {

        List<Product> products = productRepository.findByNameOrDescription("product 1",
                "product 1 description");

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findByNameAndDescriptionMethod() {

        List<Product> products = productRepository.findByNameAndDescription("product 1",
                "product 1 description");

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findFirstByName() {

        Product product = productRepository.findFirstByName("product 1");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findDistinctByNameMethod() {

        Product product = productRepository.findDistinctByName("product 1");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByPriceGreaterThanMethod() {
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(100));
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findByPriceLessThanMethod() {

        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(200));
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findByNameContainingMethod() {

        List<Product> products = productRepository.findByNameContaining("product 1");
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findByNameLikeMethod() {

        List<Product> products = productRepository.findByNameLike("product 1");
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findByPriceBetweenMethod() {
        List<Product> products = productRepository.findByPriceBetween(
                new BigDecimal(100), new BigDecimal(300)
        );

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });

    }

    @Test
    void findByDateCreatedBetweenMethod() {

        // start date
        LocalDateTime startDate = LocalDateTime.of(2022, 02, 13, 17, 48, 33);
        // end date
        LocalDateTime endDate = LocalDateTime.of(2022, 02, 13, 18, 15, 21);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findByNameInMethod() {

        List<Product> products = productRepository.findByNameIn(List.of("product 1", "product 2", "product 3"));
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findFirst2ByOrderByNameAscMethod() {
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findTop2ByOrderByPriceDescMethod() {
        List<Product> products = productRepository.findTop2ByOrderByPriceDesc();
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }


    //JPQL
    @Test
    void findByNameOrDescriptionJPQLIndexParam() {
        Product product = productRepository.findByNameOrDescriptionJPQLIndexParam("product 1", "product 1 description");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByNameOrDescriptionJPQLNamedParam() {
        Product product = productRepository.findByNameOrDescriptionJPQLNamedParam("product 1", "product 1 description");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    //Native SQL
    @Test
    void findByNameOrDescriptionSQLIndexParam() {
        Product product = productRepository.findByNameOrDescriptionSQLIndexParam("product 1", "product 1 description");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByNameOrDescriptionSQLNamedParam() {
        Product product = productRepository.findByNameOrDescriptionSQLNamedParam("product 1", "product 1 description");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }


    //Named Query
    @Test
    void findByPrice() {
        Product product = productRepository.findByPrice(new BigDecimal(100));
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByDescription() {
        List<Product> products = productRepository.findByDescription("product 1 description");
        products.stream().forEach(t -> System.out.println(t.getSku()));
    }

//    @Test
//    void findAllOrderByNameASC() {
//        List<Product> products = productRepository.findAllOrderByNameAsc();
//        products.stream().forEach(t -> System.out.println(t.getSku()));
//    }
}

