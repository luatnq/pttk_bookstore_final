package com.example.bookstore.dao.book;

import com.example.bookstore.dao.DBConnectionPool;
import com.example.bookstore.model.FileDb;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.book.Publisher;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookItemDAO {


    public List<BookItem> getBookItems(){

        Transaction transaction = null;
        List<BookItem> books = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            books = session.createQuery("from BookItem ").getResultList();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return books;
    }

    public BookItem getBookItemByBook(Book book){
        Transaction transaction = null;
        BookItem bookItem = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            bookItem = (BookItem) session.createQuery("from BookItem item where ( :book is null or item.book = :book ) ")
                    .setParameter("book", book).getSingleResult();


            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return bookItem;
    }


    public BookItem getBookItemById(Integer id){
        Transaction transaction = null;
        BookItem bookItem = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            bookItem = (BookItem) session.createQuery("from BookItem item where ( item.id = :id ) ")
                    .setParameter("id", id).getSingleResult();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return bookItem;
    }

    public void saveBookItem(BookItem bookItem){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(bookItem);

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}
