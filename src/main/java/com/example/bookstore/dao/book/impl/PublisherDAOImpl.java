package com.example.bookstore.dao.book.impl;

import com.example.bookstore.dao.book.PublisherDAO;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.book.Publisher;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PublisherDAOImpl implements PublisherDAO {

    public Publisher getPublisherByName(String name){
        Transaction transaction = null;
        Publisher publisher = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            publisher = (Publisher) session.createQuery(" from Publisher pub where ( (:name) is null or pub.name = :name) ")
                    .setParameter("name", name).getSingleResult();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                return null;
            }
            e.printStackTrace();
            return null;
        }
        return publisher;
    }

    public void savePublisher(Publisher publisher){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(publisher);

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
