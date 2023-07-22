package com.finalproject.demomodule.service.impl;

import com.finalproject.demomodule.repository.ProductRepository;
import com.finalproject.demomodule.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
}
