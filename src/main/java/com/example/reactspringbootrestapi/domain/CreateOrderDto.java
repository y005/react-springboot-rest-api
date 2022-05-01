package com.example.reactspringbootrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderDto {
    private String email;
    private String address;
    private String postcode;
    private List<OrderItem> orderItems;
}
