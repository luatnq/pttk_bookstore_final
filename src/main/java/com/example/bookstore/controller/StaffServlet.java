package com.example.bookstore.controller;

import com.example.bookstore.dao.customer.AccountDAO;
import com.example.bookstore.dao.order.CartDAO;
import com.example.bookstore.dao.order.OrderDAO;
import com.example.bookstore.dao.order.ShipmentDAO;
import com.example.bookstore.dao.order.impl.ShipmentDAOImpl;
import com.example.bookstore.dao.staff.StaffDAO;
import com.example.bookstore.dao.staff.StaffDAOImpl;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.FullName;
import com.example.bookstore.model.order.Cart;
import com.example.bookstore.model.order.Order;
import com.example.bookstore.model.order.Shipment;
import com.example.bookstore.model.staff.Staff;
import com.example.bookstore.util.HashHelper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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


        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByUsername(username);
        if (Objects.nonNull(account)) {
            System.out.println("Username exist already");
            response.sendRedirect(request.getContextPath() + "/loginError.jsp?errFlag=inv");
            return;
        }

        account = new Account(username, password);
        FullName fullName = new FullName(firstName, lastName, middleName);
        StaffDAO staffDAO = new StaffDAOImpl();
        staffDAO.saveFullName(fullName);
        staffDAO.saveAccount(account);

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
