package com.fullstack.oblig3.repo;


import com.fullstack.oblig3.dao.AddressDAO;
import com.fullstack.oblig3.dao.AuthorBookDAO;
import com.fullstack.oblig3.dao.AuthorDAO;
import com.fullstack.oblig3.dao.BookDAO;
import com.fullstack.oblig3.model.Address;
import com.fullstack.oblig3.model.Author;
import com.fullstack.oblig3.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepo
{
    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private AuthorBookDAO authorBookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private AddressDAO addressDAO;

    public List<Book> getAllBooks()
    {
        List<Book> books = bookDAO.getAll();
        books.stream().forEach(book -> {
            book.set_authors((ArrayList<Author>)authorBookDAO.getAllAuthorsByBook(book.get_ID()));
            book.get_authors().stream().forEach(author -> author.set_address(addressDAO.getOne(author.get_address().get_id())));
        });
        return books;
    }

    public Book getBook(long id)
    {
        Book book = bookDAO.getOne(id);
        if (book == null)
        {
            return null;
        }
        book.set_authors((ArrayList<Author>)authorBookDAO.getAllAuthorsByBook(book.get_ID()));
        book.get_authors().stream().forEach(author -> author.set_address(addressDAO.getOne(author.get_address().get_id())));
        return book;
    }

    public void createBook(Book book)
    {
        long id = bookDAO.create(book); //Legger boken i databasen
        for (Author a : book.get_authors()) //Legger alle forfatter bok koblingene i databasen
        {
            if (authorDAO.getOne(a.get_ID()) != null) //Velger å bare ignorere ugyldige forfattere
            {
                authorBookDAO.create(a.get_ID(), id);
            }
        }
    }

    public void updateBook(long id, Book book)
    {
        bookDAO.update(id, book);
        //Henter ut lister over id'ene til de forskjellige forfatterene
        List<Long> authorsFromDB = authorBookDAO.getAllAuthorsByBook(id).stream()
                .map(author -> author.get_ID())
                .collect(Collectors.toList()),
                allAuthors = new ArrayList<>(),
                newAuthorList = book.get_authors().stream()
                        .map(author -> author.get_ID())
                        .collect(Collectors.toList());

        allAuthors.addAll(newAuthorList);
        allAuthors.addAll(authorsFromDB);

        allAuthors = allAuthors.stream().distinct().collect(Collectors.toList()); //fjerner duplikater, unødvendig å sjekke dobbelt

        for (Long authorID : allAuthors) //Oppdaterer alle forfatter bok koblingene, mellom denne boka og alle involverte forfattere
        {
            if (newAuthorList.contains(authorID) && !authorsFromDB.contains(authorID)) //Om forfatteren skal være med, men ikke er i databasen
            {
                if (authorDAO.getOne(authorID) != null)
                {
                    authorBookDAO.create(authorID, id); //Legges til
                }
            }
            else if (!newAuthorList.contains(authorID) && authorsFromDB.contains(authorID)) //Om forfatteren ikke skal være med, men er i databasen
            {
                authorBookDAO.delete(authorID, id); //Fjernes
            }
        }
    }

    public void deleteBook(long id)
    {
        for (Author author : authorBookDAO.getAllAuthorsByBook(id))
        {
            authorBookDAO.delete(author.get_ID(), id);
        }
        bookDAO.delete(id);
    }

    public List<Book> searchBookByTitle(String title)
    {
        return new ArrayList<Book>(this.getAllBooks().stream()
                .filter(x -> x.get_title().toLowerCase().contains(title.toLowerCase()) == true)
                .collect(Collectors.toList()));
    }
}
