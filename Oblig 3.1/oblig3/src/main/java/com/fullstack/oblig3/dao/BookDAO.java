package com.fullstack.oblig3.dao;

import com.fullstack.oblig3.model.Book;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAO
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Book> getAll()
    {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(connection -> connection.prepareStatement(sql), new BookRowMapper());
    }

    public Book getOne(long id)
    {
        String sql = "SELECT * FROM book WHERE id = ?";
        List<Book> list = jdbcTemplate.query(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        }, new BookRowMapper());

        if (list.isEmpty())
        {
            return null;
        }
        if (list.size() > 1)
        {
            throw new IllegalStateException("Database integritets feil");
        }
        return list.get(0);
    }

    public long create(Book book)
    {
        String sql = "INSERT INTO book VALUES (DEFAULT, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.get_title());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void update(long id, Book book)
    {
        String sql = "UPDATE book SET title = ? WHERE id = ?";
        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.get_title());
            ps.setLong(2, id);
            return ps;
        });
    }

    public void delete(long id)
    {
        String sql = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        });
    }
}
