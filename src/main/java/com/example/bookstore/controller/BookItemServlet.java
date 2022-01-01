package com.example.bookstore.controller;

//import com.bookstore.db.BookDB;
//import com.bookstore.models.book.Book;

import com.example.bookstore.dao.book.BookDAO;
import com.example.bookstore.dao.book.BookItemDAO;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookItemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = (String) request.getParameter("isbn");
		System.out.println("Retreived ISBN: " + isbn);
		Book book = new BookDAO().getBookByIsbn(isbn);
		BookItemDAO bookItemDAO = new BookItemDAO();

		request.setAttribute("bookItem", bookItemDAO.getBookItemByBook(book));

		RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/BookListing.jsp");
        dispatcher.forward(request, response);
	}

}
