package com.example.bookstore.dao.book;

import com.example.bookstore.model.book.Author;

public interface AuthorDAO {

    Author getAuthorByName(String name);
    void saveAuthor(Author author);
}
