package com.example.bookstore.dao.book;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.BookItem;

import java.util.List;

public interface BookItemDAO {
    List<BookItem> getBookItems();
    BookItem getBookItemByBook(Book book);
    BookItem getBookItemById(Integer id);
    void saveBookItem(BookItem bookItem);
    List<BookItem> searchBookItem(String keyword);
}
