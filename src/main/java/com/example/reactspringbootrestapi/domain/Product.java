package com.example.reactspringbootrestapi.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Product {
    private long id;
    private String name;
    private Category category;
    private long price;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String name, String category, long price, String description) {
        this.name = name;
        this.price = price;
        this.category = Category.toCategory(category);
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Product(long id, String name, String category, long price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.category = Category.toCategory(category);
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setName(String name) {
        this.name = name;
        setUpdatedAt();
    }

    public void setPrice(long price) {
        this.price = price;
        setUpdatedAt();
    }

    public void setCategory(String category) {
        this.category = Category.toCategory(category);
        setUpdatedAt();
    }

    public void setDescription(String description) {
        this.description = description;
        setUpdatedAt();
    }

    private void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

    public String getCategory() {
        return this.category.toString();
    }
}
