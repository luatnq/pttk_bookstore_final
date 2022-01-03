package com.example.bookstore.dao.customer;

import com.example.bookstore.model.customer.Account;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAO {

    public Account getAccountByUsername(String username){
        Transaction transaction = null;
        Account account = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            account =(Account) session.createQuery("from Account acc where acc.username = :username ")
                    .setParameter("username", username).getSingleResult();
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                return null;
            }
            e.printStackTrace();
        }
        return account;
    }
}
