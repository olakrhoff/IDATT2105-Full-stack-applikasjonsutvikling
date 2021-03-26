package com.fullstack.oblig3.repo;


import com.fullstack.oblig3.dao.AddressDAO;
import com.fullstack.oblig3.dao.AuthorBookDAO;
import com.fullstack.oblig3.dao.AuthorDAO;
import com.fullstack.oblig3.dao.AuthorRowMapper;
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
public class AuthorRepo
{
    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private AuthorBookDAO authorBookDAO;

    public List<Address> addAddress()
    {
        return addressDAO.getAll();
    }

    public List<Author> getAllAuthors()
    {
        List<Author> list = authorDAO.getAll();
        list.stream().forEach(author ->
        {
            author.set_address(addressDAO.getOne(author.get_address().get_id()));
            author.set_books((ArrayList<Book>)authorBookDAO.getAllBooksByAuthor(author.get_ID()));
        });
        return list;
    }

    public Author getAuthor(long id)
    {
        Author author = authorDAO.getOne(id);
        if (author == null)
        {
            return null;
        }
        author.set_address(addressDAO.getOne(author.get_address().get_id()));
        author.set_books((ArrayList<Book>)authorBookDAO.getAllBooksByAuthor(author.get_ID()));
        return author;
    }

    public void createAuthor(Author author)
    {
        if (addressDAO.getOne(author.get_address().get_id()) == null) //Sjekker om addressen eksisterer fra før
        {
            long addresseID = addressDAO.create(author.get_address()); //Lager addressen om den ikke eksisterer fra før
            author.get_address().set_id(addresseID); //Setter addressens ID til den autogenererte surogatnøkkelen i databasen
        }
        authorDAO.create(author);
    }

    public void updateAuthor(long id, Author author)
    {
        if (addressDAO.getOne(author.get_address().get_id()) == null) //Sjekker om addressen eksisterer fra før
        {
            long addressID = addressDAO.create(author.get_address()); //Lager addressen om den ikke eksisterer fra før
            author.get_address().set_id(addressID); //Setter addressens ID til den autogenererte surogatnøkkelen i databasen
        }
        authorDAO.update(id, author);

        List<Long> booksFromDB = authorBookDAO.getAllBooksByAuthor(id).stream().map(book -> book.get_ID()).collect(Collectors.toList()), allBooks = new ArrayList<>(), newBookList = author.get_books().stream().map(book -> book.get_ID()).collect(Collectors.toList());

        allBooks.addAll(newBookList);
        allBooks.addAll(booksFromDB);

        allBooks = allBooks.stream().distinct().collect(Collectors.toList()); //fjerner duplikater, unødvendig å sjekke dobbelt

        for (Long bookID : allBooks) //Oppdaterer alle forfatter bok koblingene, mellom denne forfatteren og alle involverte bøker
        {
            if (newBookList.contains(bookID) && !booksFromDB.contains(bookID)) //Om forfatteren skal være med, men ikke er i databasen
            {
                authorBookDAO.create(author.get_ID(), bookID); //Legges til
            }
            else if (!newBookList.contains(bookID) && booksFromDB.contains(bookID)) //Om forfatteren ikke skal være med, men er i databasen
            {
                authorBookDAO.delete(author.get_ID(), bookID); //Fjernes
            }
        }
    }

    public void deleteAuthor(long id)
    {
        for (Book book : authorBookDAO.getAllBooksByAuthor(id))
        {
            authorBookDAO.delete(id, book.get_ID());
        }
        authorDAO.delete(id);
    }

    public List<Author> searchAuthor(String name)
    {
        return this.getAllAuthors().stream()
                .filter(x -> x.get_name().toLowerCase().contains(name.toLowerCase()) == true)
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByAuthors(String name)
    {
        List<Author> authors = this.searchAuthor(name);
        List<Book> books = new ArrayList<Book>();

        authors.stream().forEach(author ->
        {
            if (author.get_books() != null)
            {
                books.addAll(author.get_books());
            }
        });

        return books.stream().distinct().collect(Collectors.toList()); //Fjerner duplikater, men funker ikke?
    }
}
