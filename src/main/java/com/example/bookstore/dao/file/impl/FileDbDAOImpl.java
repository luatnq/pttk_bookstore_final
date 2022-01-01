package com.example.bookstore.dao.file.impl;

import com.example.bookstore.dao.file.FileDbDAO;
import com.example.bookstore.model.FileDb;
import com.example.bookstore.model.book.Author;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FileDbDAOImpl implements FileDbDAO {
    public void saveFile(FileDb fileDb){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(fileDb);

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
