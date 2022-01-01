package com.example.bookstore.dao.book.impl;

import com.example.bookstore.dao.book.AuthorDAO;
import com.example.bookstore.model.book.Author;
import com.example.bookstore.model.book.Publisher;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AuthorDAOImpl implements AuthorDAO {
    public Author getAuthorByName(String name){
        Transaction transaction = null;
        Author author = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            author = (Author) session.createQuery("from Author au where ( :name is null or au.name = :name) ")
                    .setParameter("name", name).getSingleResult();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                return null;
            }
            e.printStackTrace();
            return null;
        }
        return author;
    }

    public void saveAuthor(Author author){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(author);

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
