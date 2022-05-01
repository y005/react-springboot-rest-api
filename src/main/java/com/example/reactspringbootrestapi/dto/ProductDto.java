package com.example.reactspringbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private String category;
    private long price;
    private String description;
}
