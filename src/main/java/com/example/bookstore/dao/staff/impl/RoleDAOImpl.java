package com.example.bookstore.dao.staff.impl;

import com.example.bookstore.dao.staff.RoleDAO;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.staff.Role;
import com.example.bookstore.model.staff.Staff;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleDAOImpl implements RoleDAO {
    public Role getRoleById(Integer roleId){
        Transaction transaction = null;
        Role role = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            role = (Role) session.createQuery("from Role role where role.id = :roleId ")
                    .setParameter("roleId", roleId).getSingleResult();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                return null;
            }
            e.printStackTrace();
        }
        return role;
    }
}
