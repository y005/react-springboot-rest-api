package com.example.reactspringbootrestapi.dao;

import com.example.reactspringbootrestapi.WebAppContext;
import com.example.reactspringbootrestapi.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringJUnitConfig(classes = {WebAppContext.class})
class JdbcProductRepositoryTest {
    @Autowired
    JdbcProductRepository jdbcProductRepository;

    @BeforeEach
    void setUp() {
        jdbcProductRepository.deleteAll();
        jdbcProductRepository.insert(new Product("kanu","coffee",1000, null));
    }

    @Test
    void findAll() {
        int result = jdbcProductRepository.findAll().size();

        assertThat(result, is(1));
    }

    @Test
    void insert() {
        Product result = jdbcProductRepository.findAll().get(0);

        assertThat(result.getName(), is("kanu"));
        assertThat(result.getPrice(), is(1000l));
        assertThat(result.getCategory(), is("coffee"));
    }

    @Test
    void update() {
        Product result = jdbcProductRepository.findAll().get(0);
        result.setName("maxim");
        result.setPrice(2000);
        jdbcProductRepository.update(result);
        result = jdbcProductRepository.findAll().get(0);

        assertThat(result.getName(), is("maxim"));
        assertThat(result.getPrice(), is(2000l));
    }

    @Test
    void findById() {
        Product result = jdbcProductRepository.findAll().get(0);
        long id = result.getId();

        result = jdbcProductRepository.findById(id).get();

        assertThat(result.getName(), is("kanu"));
        assertThat(result.getPrice(), is(1000l));
        assertThat(result.getCategory(), is("coffee"));
    }

    @Test
    void findByName() {
        Product result = jdbcProductRepository.findByName("kanu").get();

        assertThat(result.getName(), is("kanu"));
        assertThat(result.getPrice(), is(1000l));
        assertThat(result.getCategory(), is("coffee"));
    }

    @Test
    void findByCategory() {
        Product result = jdbcProductRepository.findByCategory("coffee").get(0);

        assertThat(result.getName(), is("kanu"));
        assertThat(result.getPrice(), is(1000l));
        assertThat(result.getCategory(), is("coffee"));
    }

    @Test
    void deleteAll() {
        int result1 = jdbcProductRepository.findAll().size();
        jdbcProductRepository.deleteAll();
        int result2 = jdbcProductRepository.findAll().size();

        assertThat(result1, is(1));
        assertThat(result2, is(0));
    }
}