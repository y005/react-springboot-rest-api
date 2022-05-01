package com.example.reactspringbootrestapi.controller;

import com.example.reactspringbootrestapi.domain.CreateOrderDto;
import com.example.reactspringbootrestapi.domain.Order;
import com.example.reactspringbootrestapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    @ResponseBody
    public Order createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return orderService.createOrder(
                createOrderDto.getEmail(),
                createOrderDto.getAddress(),
                createOrderDto.getPostcode(),
                createOrderDto.getOrderItems()
        );
    }
}
