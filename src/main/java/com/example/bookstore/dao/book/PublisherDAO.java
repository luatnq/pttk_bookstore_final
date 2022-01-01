package com.example.bookstore.dao.book;

import com.example.bookstore.model.book.Publisher;

public interface PublisherDAO {
    Publisher getPublisherByName(String name);

    void savePublisher(Publisher publisher);
}
