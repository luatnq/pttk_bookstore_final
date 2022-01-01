package com.example.bookstore.util;

import com.example.bookstore.model.FileDb;
import com.example.bookstore.model.book.Author;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.book.Publisher;
import com.example.bookstore.model.customer.*;
import com.example.bookstore.model.order.Cart;
import com.example.bookstore.model.order.Order;
import com.example.bookstore.model.order.Shipment;
import com.example.bookstore.model.order.payment.Cash;
import com.example.bookstore.model.order.payment.Check;
import com.example.bookstore.model.order.payment.Credit;
import com.example.bookstore.model.order.payment.Payment;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.ServiceRegistryAwareService;

import java.util.Properties;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/bookstore?zeroDateTimeBehavior=convertToNull");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Author.class);
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(BookItem.class);
                configuration.addAnnotatedClass(Publisher.class);
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Address.class);
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(CustomerMember.class);
                configuration.addAnnotatedClass(CustomerNew.class);
                configuration.addAnnotatedClass(FullName.class);
                configuration.addAnnotatedClass(Cash.class);
                configuration.addAnnotatedClass(Check.class);
                configuration.addAnnotatedClass(Credit.class);
                configuration.addAnnotatedClass(Payment.class);
                configuration.addAnnotatedClass(Cart.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Shipment.class);
                configuration.addAnnotatedClass(FileDb.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Config success");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//                return sessionFactory;

                return sessionFactory;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
