package com.example.bookstore.controller;


import com.example.bookstore.dao.book.BookDAO;
import com.example.bookstore.dao.book.BookItemDAO;
import com.example.bookstore.dao.book.impl.BookDAOImpl;
import com.example.bookstore.dao.book.impl.BookItemDAOImpl;
import com.example.bookstore.dao.customer.AccountDAO;
import com.example.bookstore.dao.customer.CustomerDAO;
import com.example.bookstore.dao.customer.impl.AccountDAOImpl;
import com.example.bookstore.dao.customer.impl.CustomerDAOImpl;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.customer.Account;
import com.example.bookstore.model.customer.Customer;
import com.example.bookstore.model.order.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class BookItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookItemServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String quantityStr = request.getParameter("quantity");
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String bookItemId = request.getParameter("id");

        String removeButtonVal = request.getParameter("remove");
        String addButtonVal = request.getParameter("add");
        String quantityError = request.getParameter("quanErr");

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        if (Objects.isNull(quantityError)) {
            Object userIn = session.getAttribute("user");
            String userLoggedIn = null;
            if (userIn != null) {
                userLoggedIn = (String) userIn;
            }
            if (Objects.isNull(userLoggedIn)) {
                RequestDispatcher dispatcher =
                        getServletContext().getRequestDispatcher("/SignUp.jsp");
                dispatcher.forward(request, response);
            }
            if (Objects.isNull(cart.getCustomer())) {
                CustomerDAO customerDAO = new CustomerDAOImpl();
                AccountDAO accountDAO = new AccountDAOImpl();
                Account account = accountDAO.getAccountByUsername(userLoggedIn);
                Customer customer = customerDAO.getCustomerByAccount(account);
                cart.setCustomer(customer);
            }

            BookItemDAO bookItemDAO = new BookItemDAOImpl();

            BookItem bookItem = bookItemDAO.getBookItemById(Integer.valueOf(bookItemId));
            float sumPrice = 0f;

            if (Objects.nonNull(removeButtonVal)) {
                cart.getBookItems().remove(bookItem);
                sumPrice = cart.getTotalPrice() - bookItem.getPrice();
            } else if (Objects.nonNull(addButtonVal)) {
                cart.getBookItems().add(bookItem);
                cart.setAmount(1);
                sumPrice = cart.getTotalPrice() + bookItem.getPrice();
            }

            sumPrice = cart.getTotalPrice() + bookItem.getPrice();
            cart.setTotalPrice(sumPrice);
            session.setAttribute("totalPrice", String.valueOf(sumPrice));
        }
        session.setAttribute("cart", cart);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(Objects.nonNull(removeButtonVal) ? "/Cart.js" : "/");
        dispatcher.forward(request, response);
    }

}
