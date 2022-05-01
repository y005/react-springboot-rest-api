package com.example.reactspringbootrestapi.dao;

import com.example.reactspringbootrestapi.domain.Order;
import com.example.reactspringbootrestapi.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Order> OrderRowMapper = (resultSet,i)->{
        long id = resultSet.getLong("id");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String postcode = resultSet.getString("postcode");
        String order_status = resultSet.getString("order_status");
        LocalDateTime createdAt = resultSet.getTimestamp("createdAt").toLocalDateTime();
        LocalDateTime updatedAt =  resultSet.getTimestamp("updatedAt").toLocalDateTime();
        return new Order(id, email, address, postcode, null, order_status, createdAt, updatedAt);
    };

    private Map<String, Object> toOrderParamMap(Order order) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("email", order.getEmail());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("order_status", order.getOrderStatus());
        paramMap.put("createdAt", order.getCreatedAt().toString());
        paramMap.put("updatedAt", order.getUpdatedAt().toString());
        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(long id, LocalDateTime createdAt, LocalDateTime updatedAt, OrderItem orderItem) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("order_id", id);
        paramMap.put("product_id", orderItem.productId());
        paramMap.put("category", orderItem.category());
        paramMap.put("price", orderItem.price());
        paramMap.put("quantity", orderItem.quantity());
        paramMap.put("createdAt", createdAt);
        paramMap.put("updatedAt", updatedAt);
        return paramMap;
    }

    @Autowired
    public JdbcOrderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public Order insert(Order order) {
        namedParameterJdbcTemplate.update("insert into mysql.orders(email, address, postcode, order_status, createdAt, updatedAt) values(:email, :address, :postcode, :order_status, :createdAt, :updatedAt)",
                toOrderParamMap(order)
        );

        long id = namedParameterJdbcTemplate.queryForObject("select id from mysql.orders where email=:email order by id desc limit 1",
                Collections.singletonMap("email", order.getEmail()),
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                        long id = rs.getLong("id");
                        return id;
                    }
                }
        );

        order.getOrderItems().forEach(
                orderItem -> namedParameterJdbcTemplate.update(
                        "insert into mysql.orders_item(order_id, product_id, category, price, quantity, createdAt, updatedAt) values(:order_id, :product_id, :category, :price, :quantity, :createdAt, :updatedAt)",
                        toOrderItemParamMap(id, order.getCreatedAt(), order.getUpdatedAt(), orderItem)
                )
        );
        return order;
    }
}
