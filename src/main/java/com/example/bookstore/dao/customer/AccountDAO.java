package com.example.bookstore.dao.customer;

import com.example.bookstore.model.customer.Account;

public interface AccountDAO {
    Account getAccountByUsername(String username);

    void saveAccount(Account account);
}
