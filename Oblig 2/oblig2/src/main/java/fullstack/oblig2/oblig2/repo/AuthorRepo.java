package fullstack.oblig2.oblig2.repo;

import fullstack.oblig2.oblig2.model.Address;
import fullstack.oblig2.oblig2.model.Author;
import fullstack.oblig2.oblig2.model.Book;
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
    BookRepo bookRepo;

    //Hele denne klassen er dummy
    //Dummy authors
    private List<Author> authors;

    public AuthorRepo()
    {
        this.authors = new LinkedList<>();
        this.authors.add(new Author(this.authors.size(), "Ola Hoff", new Address()));
        this.authors.add(new Author(this.authors.size(), "Per Hoff", new Address()));
        this.authors.add(new Author(this.authors.size(), "Jens Hoff", new Address()));
    }

    public List<Author> getAllAuthors()
    {
        return this.authors;
    }

    public Author getAuthor(long id)
    {
        return this.authors.stream()
                .filter(x -> x.get_ID() == id)
                .findAny()
                .orElse(null);
    }

    public void createAuthor(Author author)
    {
        author.set_ID(this.authors.size());
        ArrayList<Book> books = new ArrayList<Book>(author.get_books().stream()
                .filter(x -> bookRepo.getAllBooks().size() > x.get_ID())
                .collect(Collectors.toList()));
        author.set_books(books);
        this.authors.add(author);
    }

    public void updateAuthor(Author author)
    {
        Author foundAuthor = this.authors.stream()
                .filter(x -> x.get_ID() == author.get_ID())
                .findAny()
                .orElseThrow(() -> new RuntimeException("Item not found"));
        ArrayList<Book> books = new ArrayList<Book>(author.get_books().stream()
                .filter(x -> bookRepo.getAllBooks().size() > x.get_ID())
                .collect(Collectors.toList()));
        if (author != null)
        {
            foundAuthor.update(author.get_name(), author.get_address(), books);
        }
    }

    public void deleteAuthor(long id)
    {
        Author foundAuthor = this.authors.stream()
                .filter(x -> x.get_ID() == id)
                .findAny()
                .orElse(null);
        if (foundAuthor != null)
        {
            this.authors.remove(foundAuthor);
        }
    }

    public ArrayList<Author> searchAuthor(String name)
    {
        return new ArrayList<Author>(this.authors.stream()
                .filter(x -> x.get_name().toLowerCase().contains(name.toLowerCase()) == true)
                .collect(Collectors.toList()));
    }

    public List<Book> searchBooksByAuthors(String name)
    {
        ArrayList<Book> returnList = new ArrayList<>();
        for (Author a : searchAuthor(name))
        {
            returnList.addAll(a.get_books());
        }
        return returnList;
    }
}
