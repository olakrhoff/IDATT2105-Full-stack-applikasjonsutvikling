package com.fullstack.oblig3.web;


import com.fullstack.oblig3.model.Book;
import com.fullstack.oblig3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
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
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createBook(@RequestBody Book book)
    {
        Map<String, Object> map = new LinkedHashMap<>();
        bookService.createBook(book);
        map.put("result", "Added");
        return map;
    }


    @PutMapping("/{id}")
    public Map<String, Object> updateBook(@PathVariable("id") long id,  @RequestBody Book book)
    {
        bookService.updateBook(id, book);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Updated");
        return map;
    }


    @DeleteMapping("/{id}")
    public Map<String, Object> deleteBook(@PathVariable("id") long id)
    {
        bookService.deleteBook(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Deleted");
        return map;
    }

    @RequestMapping("/search/{title}")
    public List<Book> searchBookByTitle(@PathVariable("title") String title)
    {
        return bookService.searchBookByTitle(title);
    }
}
