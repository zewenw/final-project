package com.finalproject.demomodule.repository.unidirection;

import com.finalproject.demomodule.entity.unidirection.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository("unidirectionalProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Define Named JPQL query
    Product findByPrice(@Param("price") BigDecimal price);

    List<Product> findByDescription(@Param("description") String description);

//    List<Product> findAllOrderByNameAsc();


    //native SQL query position and naming
    @Query(value = "select * from products p where p.name = ?1 or p.description = ?2", nativeQuery = true)
    Product findByNameOrDescriptionSQLIndexParam(String name, String description);

    @Query(value = "select * from products p where p.name = :name or p.description = :description"
            , nativeQuery = true)
    Product findByNameOrDescriptionSQLNamedParam(@Param("name") String name, @Param("description") String description);


    //JPQL query position and naming
    @Query("select p from uniProduct p where p.name = ?1 or p.description = ?2")
    Product findByNameOrDescriptionJPQLIndexParam(String name, String description);

    @Query("select p from uniProduct p where p.name = :name or p.description = :description")
    Product findByNameOrDescriptionJPQLNamedParam(@Param("name") String name, @Param("description") String description);

    //naming convention query
    List<Product> findTop2ByOrderByPriceDesc();

    List<Product> findFirst2ByOrderByNameAsc();

    List<Product> findByNameIn(List<String> strings);

    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Product> findByPriceBetween(BigDecimal bigDecimal, BigDecimal bigDecimal1);

    List<Product> findByNameLike(String s);

    List<Product> findByNameContaining(String s);

    List<Product> findByPriceLessThan(BigDecimal bigDecimal);

    List<Product> findByPriceGreaterThan(BigDecimal bigDecimal);

    Product findFirstByName(String s);

    Product findDistinctByName(String s);

    List<Product> findByNameAndDescription(String s, String s1);

    List<Product> findByNameOrDescription(String s, String s1);

    Product findByName(String s);
}
