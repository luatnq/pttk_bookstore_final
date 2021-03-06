package com.example.bookstore.dao.order.impl;

import com.example.bookstore.dao.order.CartDAO;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.order.Cart;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CartDAOImpl implements CartDAO {


    public void saveCart(Cart cart){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.save(cart);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
