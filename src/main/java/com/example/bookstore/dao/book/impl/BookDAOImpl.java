package com.example.bookstore.dao.book.impl;

import com.example.bookstore.dao.DBConnectionPool;
import com.example.bookstore.dao.book.BookDAO;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.book.Publisher;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {


    public Book getBookByIsbn(String isbn){

        Transaction transaction = null;
        Book book = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            book = (Book) session.createQuery("from Book book where book.isbn = :isbn ")
            .setParameter("isbn", isbn).getSingleResult();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return book;
    }

    public void saveBook(Book book){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(book);

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateBook(Book book){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Book> getBooks(){
        Transaction transaction = null;
        List<Book> books = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            books =  session.createQuery("from Book book order by book.id desc").getResultList();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                return null;
            }
            e.printStackTrace();
        }
        return books;
    }

}
