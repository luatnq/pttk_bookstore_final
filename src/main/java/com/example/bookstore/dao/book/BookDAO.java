package com.example.bookstore.dao.book;

import com.example.bookstore.model.book.Book;

import java.util.List;

public interface BookDAO {
    Book getBookByIsbn(String isbn);

    void saveBook(Book book);

    void updateBook(Book book);

    List<Book> getBooks();
}
