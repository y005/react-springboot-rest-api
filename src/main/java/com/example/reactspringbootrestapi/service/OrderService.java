package com.example.reactspringbootrestapi.service;

import com.example.reactspringbootrestapi.domain.Email;
import com.example.reactspringbootrestapi.domain.Order;
import com.example.reactspringbootrestapi.domain.OrderItem;

import java.util.List;

public interface OrderService {
    Order createOrder(String email, String address, String postcode, List<OrderItem> orderItems);
}
