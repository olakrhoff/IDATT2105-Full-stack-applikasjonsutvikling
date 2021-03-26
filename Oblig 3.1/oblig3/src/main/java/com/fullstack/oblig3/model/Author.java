package com.fullstack.oblig3.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Author
{
    private long _ID;
    private String _name;
    private Address _address;
    private ArrayList<Book> _books;

    public Author(long _ID, String _name, Address _address)
    {
        this._ID = _ID;
        this._name = _name;
        this._address = _address;
        this._books = new ArrayList<>();
    }

    public Author()
    {
    }

    //Getters and setters

    public long get_ID()
    {
        return _ID;
    }

    public void set_ID(long _ID)
    {
        this._ID = _ID;
    }

    public String get_name()
    {
        return _name;
    }

    public void set_name(String _name)
    {
        this._name = _name;
    }

    public Address get_address()
    {
        return _address;
    }

    public void set_address(Address _address)
    {
        this._address = _address;
    }

    public ArrayList<Book> get_books()
    {
        return _books;
    }

    public void set_books(ArrayList<Book> _books)
    {
        this._books = _books;
    }

    //Metoder


    @Override
    public boolean equals(Object object)
    {
        Author author;
        if (object instanceof Author)
        {
            author = (Author)object;
        }
        else
        {
            return false;
        }
        if (this.get_ID() == author.get_ID())
        {
            return true;
        }
        return false;
    }

    /**
     * Takes a value for every object variable, if it is 'null' (or less than 0 in the case of numberOfBooks)
     * it will not change that value of the object, if it is a 'legal' value it gets set to it.
     * @param _name
     * @param _address
     * @param _books
     */
    public void update(String _name, Address _address, ArrayList<Book> _books)
    {
        this._name = _name != null ? _name : this._name;
        this._address = _address != null ? _address : this._address;
        this._books = _books != null ? _books : this._books;
    }

    public void addBooks(ArrayList<Book> books)
    {
        this._books.addAll(books);
    }

    @Override
    public String toString()
    {
        return this.get_ID() + "\n" + this.get_name() + "\n" + this.get_address() + "\n" + this.get_books() + "\n";
    }
}
