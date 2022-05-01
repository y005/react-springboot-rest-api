package com.example.reactspringbootrestapi.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    public void testEmail() {
        assertThrows(IllegalArgumentException.class, ()->{
            Email email = new Email("y005");
        });

        Email email =  new Email("y005@naver.com");

        assertThat(email.toString(), is("y005@naver.com"));
    }
}