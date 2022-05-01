package com.example.reactspringbootrestapi.service;

import com.example.reactspringbootrestapi.dao.OrderRepository;
import com.example.reactspringbootrestapi.domain.Order;
import com.example.reactspringbootrestapi.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(String email, String address, String postcode, List<OrderItem> orderItems) {
        Order order = new Order(email, address, postcode, orderItems, "accepted");
        return orderRepository.insert(order);
    }
}
