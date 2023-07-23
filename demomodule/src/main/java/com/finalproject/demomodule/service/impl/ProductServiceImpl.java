package com.finalproject.demomodule.service.impl;

import com.finalproject.demomodule.repository.unidirection.ProductRepository;
import com.finalproject.demomodule.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource(name = "unidirectionalProductRepository")
    private ProductRepository productRepository;
}
