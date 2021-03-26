package com.fullstack.oblig3.model;

public class Address
{
    private long _id;
    private String _country;
    private String _county;
    private short _zipCode; //A four digit code
    private short _buildingNumber; //The number of the building, presume that it doesn't exceed the 'short'-range (~32k, ignoring negatives)
    private String _streetName;
    private String _city;
    private int _additionalNumbers; //Used to indicate e.g. a unit number in the building.


    /**
     * An address with complete information
     * @param _country
     * @param _county
     * @param _zipCode
     * @param _buildingNumber
     * @param _streetName
     * @param _city
     * @param _additionalNumbers
     */
    public Address(long _id, String _country, String _county, short _zipCode, short _buildingNumber, String _streetName, String _city, int _additionalNumbers)
    {
        this._id = _id;
        this._country = _country;
        this._county = _county;
        this._zipCode = _zipCode;
        this._buildingNumber = _buildingNumber;
        this._streetName = _streetName;
        this._city = _city;
        this._additionalNumbers = _additionalNumbers;
    }

    /**
     * An address doesn't always need the 'additionalNumbers'-field
     * @param _country
     * @param _county
     * @param _zipCode
     * @param _buildingNumber
     * @param _streetName
     * @param _city
     */
    public Address(long _id, String _country, String _county, short _zipCode, short _buildingNumber, String _streetName, String _city)
    {
        this._id = _id;
        this._country = _country;
        this._county = _county;
        this._zipCode = _zipCode;
        this._buildingNumber = _buildingNumber;
        this._streetName = _streetName;
        this._city = _city;
    }

    public Address(long _id)
    {
        this._id = _id;
    }

    /**
     * An empty address
     */
    public Address()
    {
    }


    //Getters and setters
    public long get_id()
    {
        return _id;
    }

    public void set_id(long _id)
    {
        this._id = _id;
    }

    public String get_country()
    {
        return _country;
    }

    public void set_country(String _country)
    {
        this._country = _country;
    }

    public String get_county()
    {
        return _county;
    }

    public void set_county(String _county)
    {
        this._county = _county;
    }

    public short get_zipCode()
    {
        return _zipCode;
    }

    public void set_zipCode(short _zipCode)
    {
        this._zipCode = _zipCode;
    }

    public short get_buildingNumber()
    {
        return _buildingNumber;
    }

    public void set_buildingNumber(short _buildingNumber)
    {
        this._buildingNumber = _buildingNumber;
    }

    public String get_streetName()
    {
        return _streetName;
    }

    public void set_streetName(String _streetName)
    {
        this._streetName = _streetName;
    }

    public String get_city()
    {
        return _city;
    }

    public void set_city(String _city)
    {
        this._city = _city;
    }

    public int get_additionalNumbers()
    {
        return _additionalNumbers;
    }

    public void set_additionalNumbers(int _additionalNumbers)
    {
        this._additionalNumbers = _additionalNumbers;
    }
}
