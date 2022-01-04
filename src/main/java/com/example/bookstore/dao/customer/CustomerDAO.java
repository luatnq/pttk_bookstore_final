package com.example.bookstore.dao.customer;

import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.Customer;

public interface CustomerDAO {
    void register(Customer customer);
    Customer getCustomerByAccount(Account account);
}
