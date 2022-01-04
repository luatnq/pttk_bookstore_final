package com.example.bookstore.controller;

import com.example.bookstore.dao.customer.AccountDAO;
import com.example.bookstore.dao.customer.CustomerDAO;
import com.example.bookstore.dao.customer.impl.AccountDAOImpl;
import com.example.bookstore.dao.customer.impl.CustomerDAOImpl;
import com.example.bookstore.dao.staff.RoleDAO;
import com.example.bookstore.dao.staff.StaffDAO;
import com.example.bookstore.dao.staff.impl.RoleDAOImpl;
import com.example.bookstore.dao.staff.impl.StaffDAOImpl;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.Customer;
import com.example.bookstore.model.staff.Role;
import com.example.bookstore.model.staff.Staff;
import com.example.bookstore.util.HashHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password empty!");
            response.sendRedirect(request.getContextPath() + "/loginError.jsp?errFlag=missing");
            return;
        }
        AccountDAO accountDAO = new AccountDAOImpl();
        Account account = accountDAO.getAccountByUsername(username);

        if (Objects.isNull(account)) {
            System.out.println("Username not known");
            response.sendRedirect(request.getContextPath() + "/loginError.jsp?errFlag=inv");
            return;
        }else if (Objects.nonNull(account)){
            CustomerDAO customerDAO = new CustomerDAOImpl();
            StaffDAO staffDAO = new StaffDAOImpl();
            Customer customer = customerDAO.getCustomerByAccount(account);
            Staff staff = staffDAO.getStaffByAccount(account);

            if (Objects.nonNull(customer)){
                session.setAttribute("role", "customer");
            }else if (Objects.nonNull(staff)){
                RoleDAO roleDAO = new RoleDAOImpl();
                Role role = roleDAO.getRoleById(staff.getRoleId());

                session.setAttribute("role", role.getName());
            }
        }

        // Made it this far, user is valid. Now compare passwords.
        String hashed = HashHelper.getHash(password);
        if (hashed.equals(account.getPassword())) {
            request.getSession().setAttribute("user", username);
        } else {
            System.out.println("Password incorrect");
            response.sendRedirect(request.getContextPath() + "/loginError.jsp?errFlag=inv");
            return;
        }
        System.out.println("getRequestURL: " + request.getRequestURL().toString());
        System.out.println("getServletPath: " + request.getServletPath());
        System.out.println("getContextPath: " + request.getContextPath());

        response.sendRedirect(request.getContextPath() + "/");
    }
}
