package fullstack.oblig2.oblig2.web;

import fullstack.oblig2.oblig2.model.Author;
import fullstack.oblig2.oblig2.model.Book;
import fullstack.oblig2.oblig2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController
{
    @Autowired
    private BookService bookService;

    @ResponseBody
    @RequestMapping("")
    public List<Book> getAllBooks()
    {
        return bookService.getAllBooks();
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public Book getBook(@PathVariable("id") long id)
    {
        return bookService.getBook(id);
    }

    @PostMapping
    public Map<String, Object> createBook(@RequestBody Book book)
    {
        Map<String, Object> map = new LinkedHashMap<>();
        bookService.createBook(book);
        map.put("result", "Added");
        return map;
    }


    @PutMapping
    public Map<String, Object> updateBook(@RequestBody Book book)
    {
        bookService.updateBook(book);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Updated");
        return map;
    }


    @ResponseBody
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteBook(@PathVariable("id") long id)
    {
        bookService.deleteBook(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Deleted");
        return map;
    }

    @ResponseBody
    @RequestMapping("/search/{title}")
    public List<Book> searchBookByTitle(@PathVariable("title") String title)
    {
        return bookService.searchBookByTitle(title);
    }
}
