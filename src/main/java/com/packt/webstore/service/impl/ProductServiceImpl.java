package com.packt.webstore.service.impl;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {

        List<Product> productList = productRepository.getAllProducts();
        return productList;

    }

    @Override
    public List<Product> getProductsByCategory(String category) {

        return productRepository.getProductsByCategory(category);

    }

}
