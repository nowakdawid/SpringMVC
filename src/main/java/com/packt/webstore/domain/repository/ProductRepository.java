package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepository {

    List<Product> getAllProducts();
    Product getProductById(String productID);
    List<Product> getProductsByCategory(String category);
    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
    List<Product> getProductsByManufacturer(String manufacturer);
    Set<Product> getProductsByPrice(Map<String, List<String>> priceParams);
    void addProduct(Product product);

}
