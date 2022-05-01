package com.example.reactspringbootrestapi.dao;

import com.example.reactspringbootrestapi.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcProductRepository implements ProductRepository{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Product> productRowMapper = (resultSet, i)->{
        long id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String category = resultSet.getString("category");
        long price = resultSet.getLong("price");
        String description = resultSet.getString("description");
        LocalDateTime createAt = resultSet.getTimestamp("createdAt").toLocalDateTime();
        LocalDateTime updateAt = resultSet.getTimestamp("updatedAt").toLocalDateTime();
        return new Product(id, name, category, price, description, createAt, updateAt);
    };

    @Autowired
    public JdbcProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return namedParameterJdbcTemplate.query(
            "select * from mysql.product",
                Collections.emptyMap(),
                productRowMapper
        );
    }

    @Override
    public void insert(Product product) {
        Map<String, Object> paramMap  = new HashMap<>();

        paramMap.put("name", product.getName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("createdAt", product.getCreatedAt());
        paramMap.put("updatedAt", product.getUpdatedAt());

        namedParameterJdbcTemplate.update("insert into mysql.product(name, category, price, description, createdAt, updatedAt) values(:name, :category, :price, :description, :createdAt, :updatedAt)",
                    paramMap
                );

    }

    @Override
    public void update(Product product) {
        Map<String, Object> paramMap  = new HashMap<>();

        paramMap.put("id", product.getId());
        paramMap.put("name", product.getName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("updatedAt", product.getUpdatedAt());

        namedParameterJdbcTemplate.update("update mysql.product set name=:name, category=:category, price=:price, description=:description, updatedAt=:updatedAt where id = :id",
                paramMap
        );
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(
                namedParameterJdbcTemplate.queryForObject(
                        "select * from mysql.product where id = :id",
                        Collections.singletonMap("id", id),
                        productRowMapper
                )
        );
    }

    @Override
    public Optional<Product> findByName(String name) {
        return Optional.ofNullable(
                namedParameterJdbcTemplate.queryForObject(
                        "select * from mysql.product where name = :name",
                        Collections.singletonMap("name", name),
                        productRowMapper
                )
        );
    }

    @Override
    public List<Product> findByCategory(String category) {
        return namedParameterJdbcTemplate.query(
                "select * from mysql.product where category=:category",
                Collections.singletonMap("category", category),
                productRowMapper
        );
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.update("delete from mysql.product", Collections.emptyMap());
    }
}
