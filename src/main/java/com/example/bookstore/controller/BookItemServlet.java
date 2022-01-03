package com.example.bookstore.controller;


import com.example.bookstore.dao.book.BookDAO;
import com.example.bookstore.dao.book.impl.BookDAOImpl;
import com.example.bookstore.dao.book.BookItemDAO;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;

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
        String preIsbn = request.getParameter("isbn");
        String pushConfirm = request.getParameter("pushConfirm");
        HttpSession session = request.getSession();

        String isbn = Objects.isNull(preIsbn) ? null : preIsbn.substring(0, preIsbn.length() - 2);

        String removeButtonVal = request.getParameter("remove");
        String pushButtonVal = request.getParameter("push");
        System.out.println("Retreived ISBN: " + isbn);
        Book book = null;
        if (Objects.isNull(pushConfirm)) {
            book = new BookDAOImpl().getBookByIsbn(isbn);
            session.setAttribute("bookPush", book);
        } else {
            book = (Book) session.getAttribute("bookPush");
            session.removeAttribute("bookPush");
        }
        BookItemDAO bookItemDAO = new BookItemDAO();
        BookDAO bookDAO = new BookDAOImpl();

        if (Objects.nonNull(pushButtonVal)) {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher("/BookItemInput.jsp");
            dispatcher.forward(request, response);
        } else if (Objects.nonNull(pushConfirm)) {

            String price = request.getParameter("price");
            String discount = request.getParameter("discount");
            String barcode = request.getParameter("barCode");
//            for (FileDb fileDb : new ArrayList<>(book.getFileDbs())){
//                fileDb.setId(null);
//            }
            BookItem bookItem = new BookItem(book, Float.valueOf(price), discount, barcode);
            bookItemDAO.saveBookItem(bookItem);
            book.setStatus(true);
            bookDAO.updateBook(book);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ViewBooks.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("bookItem", bookItemDAO.getBookItemByBook(book));

            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher("/BookListing.jsp");
            dispatcher.forward(request, response);
        }
    }

}
