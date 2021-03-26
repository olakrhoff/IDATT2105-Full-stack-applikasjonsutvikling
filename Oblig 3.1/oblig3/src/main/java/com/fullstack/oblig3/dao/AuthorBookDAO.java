package com.fullstack.oblig3.dao;

import com.fullstack.oblig3.model.Author;
import com.fullstack.oblig3.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorBookDAO
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(long idAuthor, long idBook)
    {
        String sql = "INSERT INTO authorBook VALUES (?, ?)";
        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, idAuthor);
            ps.setLong(2, idBook);
            return ps;
        });
    }

    public List<Author> getAllAuthorsByBook(long id)
    {
        String sql = "SELECT a.id, a.name, a.addressID FROM authorBook ab JOIN author a ON ab.authorId = a.id WHERE bookID = ?";
        return jdbcTemplate.query(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        }, new AuthorRowMapper());

    }

    public void delete(long idAuthor, long idBook)
    {
        String sql = "DELETE FROM authorBook WHERE authorID = ? AND bookID = ?";
        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, idAuthor);
            ps.setLong(2, idBook);
            return ps;
        });
    }

    public List<Book> getAllBooksByAuthor(long id)
    {
        String sql = "SELECT b.id, b.title FROM authorBook ab JOIN book b ON ab.bookID = b.id WHERE authorID = ?";
        return jdbcTemplate.query(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        }, new BookRowMapper());
    }
}
