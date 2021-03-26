package com.fullstack.oblig3.dao;

import com.fullstack.oblig3.model.Address;
import com.fullstack.oblig3.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author>
{
    @Override
    public Author mapRow(final ResultSet resultSet, final int i) throws SQLException
    {
        Author author = new Author();

        author.set_ID(resultSet.getInt(1));
        author.set_name(resultSet.getString(2));
        author.set_address(new Address(resultSet.getLong(3))); //Setter en address med kun id, henter ikke selve Address fra databasen før vi må

        return author;
    }
}
