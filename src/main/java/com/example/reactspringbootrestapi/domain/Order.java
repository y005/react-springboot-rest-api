package com.example.reactspringbootrestapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private long id;
    private final Email email;
    private String address;
    private String postcode;
    private final List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(long id, String email, String address, String postcode, List<OrderItem> orderItems, String orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = new Email(email);
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
        this.orderStatus = OrderStatus.toOrderStatus(orderStatus);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Order(String email, String address, String postcode, List<OrderItem> orderItems, String orderStatus) {
        this.email = new Email(email);
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
        this.orderStatus = OrderStatus.toOrderStatus(orderStatus);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    private void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

    public String getEmail() {
        return email.toString();
    }

    public String getOrderStatus() {
        return orderStatus.toString();
    }
}
