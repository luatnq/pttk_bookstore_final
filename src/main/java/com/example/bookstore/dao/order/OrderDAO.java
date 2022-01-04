package com.example.bookstore.dao.order;

import com.example.bookstore.model.order.Order;

public interface OrderDAO {
    void saveOrder(Order order);
}
