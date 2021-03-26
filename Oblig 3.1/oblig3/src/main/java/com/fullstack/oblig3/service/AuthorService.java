package com.fullstack.oblig3.service;


import com.fullstack.oblig3.model.Address;
import com.fullstack.oblig3.model.Author;
import com.fullstack.oblig3.model.Book;
import com.fullstack.oblig3.repo.AuthorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepo authorRepo;

    Logger logger = LoggerFactory.getLogger(AuthorService.class);

    public List<Author> getAllAuthors()
    {
        return authorRepo.getAllAuthors();
    }

    public Author getAuthor(long id)
    {
        return authorRepo.getAuthor(id);
    }

    public void createAuthor(Author author)
    {
        authorRepo.createAuthor(author);
    }

    public void updateAuthor(long id, Author author)
    {
        authorRepo.updateAuthor(id, author);
    }

    public void deleteAuthor(long id)
    {
        authorRepo.deleteAuthor(id);
    }

    public ArrayList<Author> searchAuthor(String name)
    {
        logger.trace("Search for author by name: value " + name);
        ArrayList<Author> list = new ArrayList<>();
        for (String keyword : name.split("&"))
        {
            list.addAll(authorRepo.searchAuthor(keyword));
        }
        return (ArrayList<Author>)list.stream().distinct().collect(Collectors.toList());
    }

    public List<Book> searchBooksByAuthors(String name)
    {
        logger.trace("Search for book by author name: value " + name);
        return authorRepo.searchBooksByAuthors(name);
    }
}
