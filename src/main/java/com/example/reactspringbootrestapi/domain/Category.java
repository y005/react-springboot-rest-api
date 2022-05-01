package com.example.reactspringbootrestapi.domain;

import java.util.Arrays;

public enum Category {
    COFFEE("coffee"),
    MILK("milk"),
    BREAD("bread"),
    ;

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public static Category toCategory(String category) {
        return Arrays.stream(values()).filter(e->e.toString().equals(category)).findFirst().get();
    }

    @Override
    public String toString() {
        return category;
    }
}
