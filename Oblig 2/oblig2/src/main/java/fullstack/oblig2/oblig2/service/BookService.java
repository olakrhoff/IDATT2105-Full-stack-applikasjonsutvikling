package fullstack.oblig2.oblig2.service;

import fullstack.oblig2.oblig2.model.Book;
import fullstack.oblig2.oblig2.repo.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{
    @Autowired
    BookRepo bookRepo;

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

    public void updateBook(Book book)
    {
        bookRepo.updateBook(book);
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
