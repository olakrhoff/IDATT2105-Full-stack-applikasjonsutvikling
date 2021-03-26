package com.fullstack.oblig3.dao;

import com.fullstack.oblig3.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book>
{
    @Override
    public Book mapRow(final ResultSet resultSet, final int i) throws SQLException
    {
        Book book = new Book();

        book.set_ID(resultSet.getInt(1));
        book.set_title(resultSet.getString(2));

        return book;
    }
}
