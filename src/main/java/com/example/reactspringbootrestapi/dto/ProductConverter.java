package com.example.reactspringbootrestapi.dto;

import com.example.reactspringbootrestapi.domain.Product;

public class ProductConverter {
    public static ProductDto toProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getDescription());
    }

    public Product toProduct(ProductDto productDto) {
        return new Product(productDto.getName(), productDto.getCategory(), productDto.getPrice(), productDto.getDescription());
    }
}
