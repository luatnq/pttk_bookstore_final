package com.example.bookstore.dao.customer.impl;

import com.example.bookstore.dao.customer.CustomerDAO;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.Customer;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDAOImpl implements CustomerDAO {

    public void register(Customer customer) {
        Transaction transaction = null;
//         = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Customer getCustomerByAccount(Account account) {
        Transaction transaction = null;
        Customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            customer = (Customer) session.createQuery("from Customer cus where cus.accountId = :accountId ")
                    .setParameter("accountId", account.getId()).getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                return null;
            }
            e.printStackTrace();
        }
        return  customer;
    }

}
