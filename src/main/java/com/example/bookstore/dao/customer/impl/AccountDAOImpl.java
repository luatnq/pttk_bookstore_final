package com.example.bookstore.dao.customer.impl;

import com.example.bookstore.dao.customer.AccountDAO;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountDAOImpl implements AccountDAO {

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

    public void saveAccount(Account account) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(account);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
