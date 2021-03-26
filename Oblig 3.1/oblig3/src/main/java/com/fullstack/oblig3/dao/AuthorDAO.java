package com.fullstack.oblig3.dao;

import com.fullstack.oblig3.model.Address;
import com.fullstack.oblig3.model.Author;
import com.fullstack.oblig3.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class AuthorDAO
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Author> getAll()
    {
        String sql = "SELECT * FROM author";
        return jdbcTemplate.query(connection -> connection.prepareStatement(sql), new AuthorRowMapper());
    }

    public Author getOne(long id)
    {
        String sql = "SELECT * FROM author WHERE id = ?";
        List<Author> list = jdbcTemplate.query(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        }, new AuthorRowMapper());

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

    public void create(Author author)
    {
        //Bøker tas ikke hensyn til når en lager en forfatter, bøker kan bli lagt til en forfatter i senere tid
        String sql = "INSERT INTO author VALUES (DEFAULT, ?, ?)";
        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, author.get_name());
            ps.setLong(2, author.get_address().get_id());
            return ps;
        });
    }

    public void update(long id, Author author)
    {
        String sql = "UPDATE author SET name = ?, addressID = ? WHERE id = ?";
        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, author.get_name());
            ps.setLong(2, author.get_address().get_id());
            ps.setLong(3, id);
            return ps;
        });
    }

    public void delete(long id)
    {
        String sql = "DELETE FROM author WHERE id = ?";
        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        });
    }
}
