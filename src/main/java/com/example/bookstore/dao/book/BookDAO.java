package com.example.bookstore.dao.book;

import com.example.bookstore.dao.DBConnectionPool;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.book.Publisher;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {


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

}
