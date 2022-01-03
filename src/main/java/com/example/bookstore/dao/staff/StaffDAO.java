package com.example.bookstore.dao.staff;

import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.FullName;
import com.example.bookstore.model.staff.Staff;

import java.util.List;

public interface StaffDAO {
    void saveStaff(Staff staff);
    List<Staff> getStaffs();
    void saveAccount(Account account);
    void saveFullName(FullName fullName);
}
