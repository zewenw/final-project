package com.finalproject.demomodule.repository;

import com.finalproject.demomodule.entity.unidirection.Product;
import com.finalproject.demomodule.repository.unidirection.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    void pagination(){

        int pageNo = 3;
        int pageSize = 5;

        // create pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize);


        // findAll method and pass pageable instance
        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();

        products.forEach(System.out::println);

        // total pages
        int totalPage = page.getTotalPages();
        // total elements
        long totalElements = page.getTotalElements();
        // number of elements
        int numberOfElements = page.getNumberOfElements();
        // size
        int size = page.getSize();

        // last
        boolean isLast = page.isLast();
        // first
        boolean isFirst = page.isFirst();
        System.out.println("total page -> " + totalPage);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println(" size ->" + size);
        System.out.println(" isLast -> " + isLast);
        System.out.println(" isFirst -> " + isFirst);
    }

    @Test
    void sorting(){

        String sortBy = "price";

        Sort sort = Sort.by(sortBy).descending();

        List<Product> products = productRepository.findAll(sort);

        products.forEach(System.out::println);
    }

    @Test
    void sortingByMultipleFields(){
        String sortBy = "name";
        String sortByDesc = "description";

        Sort sortByName = Sort.by(sortBy).descending();

        Sort sortByDescription = Sort.by(sortByDesc).ascending();

        Sort groupBySort = sortByName.and(sortByDescription);

        List<Product> products = productRepository.findAll(groupBySort);

        products.forEach(System.out::println);
    }

    @Test
    void paginationAndSortingTogether(){

        String sortBy = "price";
        int pageNo = 1;
        int pageSize = 5;

        // Sort object
        Sort sort = Sort.by(sortBy).descending();

        // Pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();

        products.forEach(System.out::println);

        // total pages
        int totalPage = page.getTotalPages();
        // total elements
        long totalElements = page.getTotalElements();
        // number of elements
        int numberOfElements = page.getNumberOfElements();
        // size
        int size = page.getSize();

        // last
        boolean isLast = page.isLast();
        // first
        boolean isFirst = page.isFirst();
        System.out.println("total page -> " + totalPage);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println(" size ->" + size);
        System.out.println(" isLast -> " + isLast);
        System.out.println(" isFirst -> " + isFirst);
    }

}
