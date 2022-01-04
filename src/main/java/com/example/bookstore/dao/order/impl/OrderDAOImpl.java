package com.example.bookstore.dao.order.impl;

import com.example.bookstore.dao.order.OrderDAO;
import com.example.bookstore.model.order.Cart;
import com.example.bookstore.model.order.Order;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    public void saveOrder(Order order) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    public List<Order> getOrders(){
//
//    }

}
