package com.example.reactspringbootrestapi.dao;

import com.example.reactspringbootrestapi.domain.Category;
import com.example.reactspringbootrestapi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    void insert(Product product);

    void update(Product product);

    Optional<Product> findById(long id);

    Optional<Product> findByName(String name);

    List<Product> findByCategory(String category);

    void deleteAll();
}
