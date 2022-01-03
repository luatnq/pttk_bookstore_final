package com.example.bookstore.dao.staff;

import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.FullName;
import com.example.bookstore.model.order.Order;
import com.example.bookstore.model.staff.Staff;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO{

    public void saveStaff(Staff staff) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(staff);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
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

    public List<Staff> getStaffs(){

        Transaction transaction = null;
        List<Staff> staffs = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            staffs = session.createQuery("from Staff ").getResultList();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return staffs;
    }

}
