package com.example.reactspringbootrestapi.service;

import com.example.reactspringbootrestapi.dao.ProductRepository;
import com.example.reactspringbootrestapi.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String name, String category, long price, String description) {
        Product product = new Product(name, category, price, description);
        productRepository.insert(product);
        return product;
    }

    @Override
    public Product updateProduct(String name, String category, long price, String description) {
        Product product = new Product(name, category, price, description);
        productRepository.update(product);
        return product;
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
