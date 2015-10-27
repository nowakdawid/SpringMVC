package com.packt.webstore.service;

import com.packt.webstore.domain.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(String id);
    List<Product> getProductsByManufacturer(String manufacturer);
    List<Product> getProductsByCategory(String category);
    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
    Set<Product> getProductsByPrice(Map<String, List<String>> priceParams);
    void addProduct(Product product);

}
