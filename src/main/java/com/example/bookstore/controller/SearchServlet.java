package com.example.bookstore.controller;

import com.example.bookstore.dao.book.BookItemDAO;
import com.example.bookstore.dao.book.impl.BookItemDAOImpl;
import com.example.bookstore.model.book.BookItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("search-query");
        System.out.println("In Search  Servlet");
        BookItemDAO bookItemDAO = new BookItemDAOImpl();
        List<BookItem> bookItems = bookItemDAO.searchBookItem(query);
        request.setAttribute("books", bookItems);
        HttpSession session = request.getSession();
        session.setAttribute("search-result", bookItems);
        request.getRequestDispatcher("Search.jsp").forward(request, response);
    }
}
