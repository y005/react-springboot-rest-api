package com.example.reactspringbootrestapi.domain;

public record OrderItem(long productId, String category, long price, int quantity) {
}
