package com.example.reactspringbootrestapi.service;

import com.example.reactspringbootrestapi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Optional<Product> getProductById(long id);

    public Optional<Product> getProductByName(String name);

    public List<Product> getProductByCategory(String category);

    public List<Product> getAllProducts();

    public Product createProduct(String name, String category, long price, String description);

    public Product updateProduct(String name, String category, long price, String description);

    public void deleteAll();
}
