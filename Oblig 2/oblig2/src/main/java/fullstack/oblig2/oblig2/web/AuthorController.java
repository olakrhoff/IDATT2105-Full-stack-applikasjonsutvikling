package fullstack.oblig2.oblig2.web;

import fullstack.oblig2.oblig2.model.Address;
import fullstack.oblig2.oblig2.model.Author;
import fullstack.oblig2.oblig2.model.Book;
import fullstack.oblig2.oblig2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;

    @ResponseBody
    @RequestMapping("")
    public List<Author> getAllAuthors()
    {
        return authorService.getAllAuthors();
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public Author getAuthor(@PathVariable("id") long id)
    {
        return authorService.getAuthor(id);
    }

    @PostMapping
    public Map<String, Object> createAuthor(@RequestBody Author author)
    {
        authorService.createAuthor(author);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Added");
        return map;
    }


    @PutMapping
    public Map<String, Object> updateAuthor(@RequestBody Author author)
    {
        authorService.updateAuthor(author);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Updated");
        return map;
    }


    @ResponseBody
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteAuthor(@PathVariable("id") long id)
    {
        authorService.deleteAuthor(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "Deleted");
        return map;
    }

    @ResponseBody
    @RequestMapping("/search/{name}")
    public List<Author> searchAuthors(@PathVariable("name") String name)
    {
        return authorService.searchAuthor(name);
    }

    @ResponseBody
    @RequestMapping("/searchBook/{name}")
    public List<Book> searchBooksByAuthors(@PathVariable("name") String name)
    {
        return authorService.searchBooksByAuthors(name);
    }
}
