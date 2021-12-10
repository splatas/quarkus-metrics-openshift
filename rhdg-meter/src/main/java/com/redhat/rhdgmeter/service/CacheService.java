package com.redhat.rhdgmeter.service;

import java.util.List;

import com.redhat.rhdgmeter.model.Book;

public interface CacheService {

    public void createCache(String cacheName);

    public Book saveBook(Book book);

    public Book getBook(Book book);
    
    public List<Book> getBooks();

    public void clear();
}
