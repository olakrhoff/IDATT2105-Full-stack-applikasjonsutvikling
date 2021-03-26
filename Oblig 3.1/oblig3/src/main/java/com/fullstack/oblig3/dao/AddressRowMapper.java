package com.fullstack.oblig3.dao;

import com.fullstack.oblig3.model.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address>
{

    @Override
    public Address mapRow(final ResultSet resultSet, final int i) throws SQLException
    {
        Address address = new Address();

        address.set_id(resultSet.getLong(1));
        address.set_country(resultSet.getString(2));
        address.set_county(resultSet.getString(3));
        address.set_zipCode(resultSet.getShort(4));
        address.set_buildingNumber(resultSet.getShort(5));
        address.set_streetName(resultSet.getString(6));
        address.set_city(resultSet.getString(7));
        address.set_additionalNumbers(resultSet.getInt(8));

        return address;
    }
}
