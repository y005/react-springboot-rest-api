package com.example.reactspringbootrestapi.dao;

import com.example.reactspringbootrestapi.domain.Order;

public interface OrderRepository {
    Order insert(Order order);
}
