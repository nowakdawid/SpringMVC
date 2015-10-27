package com.packt.webstore.service.impl;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        return productRepository.getProductsByFilter(filterParams);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> getProductsByManufacturer(String manufacturer) {
        return productRepository.getProductsByManufacturer(manufacturer);
    }

    @Override
    public Set<Product> getProductsByPrice(Map<String, List<String>> priceParams) {
        return productRepository.getProductsByPrice(priceParams);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

}
