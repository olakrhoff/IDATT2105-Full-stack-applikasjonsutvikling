package com.fullstack.oblig3.dao;

import com.fullstack.oblig3.model.Address;
import com.fullstack.oblig3.model.Author;
import com.fullstack.oblig3.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.NamedParameterJdbcOperationsDependsOnPostProcessor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDAO
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Address> getAll()
    {
        return jdbcTemplate.query("SELECT * FROM address", new AddressRowMapper());
    }

    public Address getOne(long id)
    {
        String sql = "SELECT * FROM address WHERE id = ?";
        List<Address> list = jdbcTemplate.query(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps;
        }, new AddressRowMapper());

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

    public long create(Address address)
    {
        String sql = "INSERT INTO address VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection ->
        {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.get_country());
            ps.setString(2, address.get_county());
            ps.setShort(3, address.get_zipCode());
            ps.setShort(4, address.get_buildingNumber());
            ps.setString(5, address.get_streetName());
            ps.setString(6, address.get_city());
            ps.setInt(7, address.get_additionalNumbers());

            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
