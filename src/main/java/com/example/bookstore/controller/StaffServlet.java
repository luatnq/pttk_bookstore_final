package com.example.bookstore.controller;

import com.example.bookstore.dao.customer.AccountDAO;
import com.example.bookstore.dao.customer.FullNameDAO;
import com.example.bookstore.dao.customer.impl.AccountDAOImpl;
import com.example.bookstore.dao.customer.impl.FullNamDAOImpl;
import com.example.bookstore.dao.staff.StaffDAO;
import com.example.bookstore.dao.staff.impl.StaffDAOImpl;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.FullName;
import com.example.bookstore.model.staff.Staff;
import com.example.bookstore.util.HashHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class StaffServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public StaffServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = HashHelper.getHash("1234");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String staffCode = request.getParameter("staffCode");


        AccountDAOImpl accountDAOImpl = new AccountDAOImpl();
        Account account = accountDAOImpl.getAccountByUsername(username);
        if (Objects.nonNull(account)) {
            System.out.println("Username exist already");
            response.sendRedirect(request.getContextPath() + "/loginError.jsp?errFlag=inv");
            return;
        }

        account = new Account(username, password);
        FullName fullName = new FullName(firstName, lastName, middleName);
        FullNameDAO fullNameDAO = new FullNamDAOImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        StaffDAO staffDAO = new StaffDAOImpl();

        fullNameDAO.saveFullName(fullName);
        accountDAO.saveAccount(account);

        Staff staff = null;
        try {
            staff = new Staff(email, phone, dob, account, fullName, staffCode);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        staffDAO.saveStaff(staff);
        int i = 0;


        response.sendRedirect(request.getContextPath() + "/");
    }
}
