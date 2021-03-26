package fullstack.oblig2.oblig2.service;

import fullstack.oblig2.oblig2.model.Author;
import fullstack.oblig2.oblig2.model.Book;
import fullstack.oblig2.oblig2.repo.AuthorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService
{
    @Autowired
    AuthorRepo authorRepo;

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

    public void updateAuthor(Author author)
    {
        authorRepo.updateAuthor(author);
    }

    public void deleteAuthor(long id)
    {
        authorRepo.deleteAuthor(id);
    }

    public ArrayList<Author> searchAuthor(String name)
    {
        logger.trace("Search for author by name: value " + name);
        return authorRepo.searchAuthor(name);
    }

    public List<Book> searchBooksByAuthors(String name)
    {
        logger.trace("Search for book by author name: value " + name);
        return authorRepo.searchBooksByAuthors(name);
    }
}
