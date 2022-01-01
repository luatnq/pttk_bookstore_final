package com.example.bookstore.dao.order.impl;

import com.example.bookstore.dao.order.ShipmentDAO;
import com.example.bookstore.model.order.Shipment;
import com.example.bookstore.model.order.payment.Cash;
import com.example.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ShipmentDAOImpl implements ShipmentDAO {
    public void saveShipment(Shipment shipment){
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.save(shipment);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
