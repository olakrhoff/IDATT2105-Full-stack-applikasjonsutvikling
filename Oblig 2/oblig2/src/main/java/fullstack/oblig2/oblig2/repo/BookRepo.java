package fullstack.oblig2.oblig2.repo;

import fullstack.oblig2.oblig2.model.Author;
import fullstack.oblig2.oblig2.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepo
{
    //Hele klassen er dummy
    private List<Book> books;

    public BookRepo()
    {
        this.books = new LinkedList<>();
        this.books.add(new Book(this.books.size(), "Kaker", new ArrayList<Author>()));
        this.books.add(new Book(this.books.size(), "Kaker 2", new ArrayList<Author>()));
        this.books.add(new Book(this.books.size(), "Kaker 3", new ArrayList<Author>()));
    }

    public List<Book> getAllBooks()
    {
        return this.books;
    }

    public Book getBook(long id)
    {
        return this.books.stream()
                .filter(x -> x.get_ID() == id)
                .findAny()
                .orElse(null);
    }

    public void createBook(Book book)
    {
        book.set_ID(this.books.size());
        this.books.add(book);
    }

    public void updateBook(Book book)
    {
        Book foundBook = this.books.stream()
                .filter(x -> x.get_ID() == book.get_ID())
                .findAny()
                .orElseThrow(() -> new RuntimeException("Item not found"));
        if (book != null)
        {
            foundBook.update(book.get_title(), book.get_authors());
        }
    }

    public void deleteBook(long id)
    {
        Book foundBook = this.books.stream()
                .filter(x -> x.get_ID() == id)
                .findAny()
                .orElse(null);
        if (foundBook != null)
        {
            this.books.remove(foundBook);
        }
    }

    public List<Book> searchBookByTitle(String title)
    {
        return new ArrayList<Book>(this.books.stream()
                .filter(x -> x.get_title().toLowerCase().contains(title.toLowerCase()) == true)
                .collect(Collectors.toList()));
    }
}
