package com.example.bookstore.dao.order;

import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.order.Cart;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CartDAO {

    public Cart getCartByCustomerId(Integer customerId){
        Transaction transaction = null;
        Cart cart = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            cart =(Cart) session.createQuery("from Cart cart where cart.customer.id = :customerId ")
                    .setParameter("customerId", customerId).getSingleResult();
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cart;
    }

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
