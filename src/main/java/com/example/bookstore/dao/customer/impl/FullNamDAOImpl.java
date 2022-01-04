package com.example.bookstore.dao.customer.impl;

import com.example.bookstore.dao.customer.FullNameDAO;
import com.example.bookstore.model.customer.FullName;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FullNamDAOImpl implements FullNameDAO {
    public void saveFullName(FullName fullName) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(fullName);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
