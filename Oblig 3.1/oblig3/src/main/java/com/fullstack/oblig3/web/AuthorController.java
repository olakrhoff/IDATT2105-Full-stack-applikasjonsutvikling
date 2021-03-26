package com.fullstack.oblig3.web;

import com.fullstack.oblig3.model.Address;
import com.fullstack.oblig3.model.Author;
import com.fullstack.oblig3.model.Book;
import com.fullstack.oblig3.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/authors")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("")
    public List<Author> getAllAuthors()
    {
        return authorService.getAllAuthors();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}")
    public Author getAuthor(@PathVariable("id") long id)
    {
        Author author = authorService.getAuthor(id);
        return author;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Map<String, Object> createAuthor(@RequestBody Author author)
    {
        authorService.createAuthor(author);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Added");
        return map;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateAuthor(@PathVariable("id") long id, @RequestBody Author author)
    {
        authorService.updateAuthor(id, author);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") long id)
    {
        authorService.deleteAuthor(id);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/search/{name}")
    public List<Author> searchAuthors(@PathVariable("name") String name)
    {
        return authorService.searchAuthor(name);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/searchBook/{name}")
    public List<Book> searchBooksByAuthors(@PathVariable("name") String name)
    {
        return authorService.searchBooksByAuthors(name);
    }
}
