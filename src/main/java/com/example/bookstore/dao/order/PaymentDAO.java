package com.example.bookstore.dao.order;

import com.example.bookstore.model.order.payment.Cash;
import com.example.bookstore.model.order.payment.Check;
import com.example.bookstore.model.order.payment.Credit;

public interface PaymentDAO {
    void savePayment(Cash cash);
    void savePayment(Check check);
    void savePayment(Credit credit);
}
