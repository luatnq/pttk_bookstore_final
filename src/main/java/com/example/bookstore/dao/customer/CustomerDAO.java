package com.example.bookstore.dao.customer;

import com.example.bookstore.model.customer.Customer;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDAO {

    public void register(Customer customer) {
        Transaction transaction = null;
//         = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
//            account =(Account) session.createQuery("from Account acc where acc.username = :username ")
//                    .setParameter("username", username).getSingleResult();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Customer getCustomerByUsername(String username) {
        Transaction transaction = null;
        Customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            customer = (Customer) session.createQuery("from Customer cus where cus.account.username = :username ")
                    .setParameter("username", username).getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return  customer;
    }

}
