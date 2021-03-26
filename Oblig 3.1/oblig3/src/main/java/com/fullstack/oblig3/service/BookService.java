package com.fullstack.oblig3.service;


import com.fullstack.oblig3.model.Book;
import com.fullstack.oblig3.repo.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{
    @Autowired
    private BookRepo bookRepo;

    Logger logger = LoggerFactory.getLogger(BookService.class);

    public List<Book> getAllBooks()
    {
        return bookRepo.getAllBooks();
    }

    public Book getBook(long id)
    {
        return bookRepo.getBook(id);
    }

    public void createBook(Book book)
    {
        bookRepo.createBook(book);
    }

    public void updateBook(long id, Book book)
    {
        bookRepo.updateBook(id, book);
    }

    public void deleteBook(long id)
    {
        bookRepo.deleteBook(id);
    }

    public List<Book> searchBookByTitle(String title)
    {
        logger.trace("Search for book by title: value " + title);
        return bookRepo.searchBookByTitle(title);
    }
}
