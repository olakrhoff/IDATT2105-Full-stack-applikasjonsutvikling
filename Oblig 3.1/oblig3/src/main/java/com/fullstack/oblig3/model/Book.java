package com.fullstack.oblig3.model;

import java.util.ArrayList;
import java.util.Date;

public class Book
{
    private long _ID;
    private String _title;
    private ArrayList<Author> _authors;

    public Book(long _ID, String _title, ArrayList<Author> _authors)
    {
        this._ID = _ID;
        this._title = _title;
        this._authors = _authors;
    }

    public Book()
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

    public String get_title()
    {
        return _title;
    }

    public void set_title(String _title)
    {
        this._title = _title;
    }

    public ArrayList<Author> get_authors()
    {
        return _authors;
    }

    public void set_authors(ArrayList<Author> _authors)
    {
        this._authors = _authors;
    }

    //Metoder

    @Override
    public boolean equals(Object object)
    {
        Book book;
        if (object instanceof Book)
        {
            book = (Book)object;
        }
        else
        {
            return false;
        }
        if (this.get_ID() == book.get_ID())
        {
            return true;
        }
        return false;
    }

    /**
     * Takes a value for every object variable, if it is 'null' it will not change
     * that value of the object, if it is a 'legal' value it gets set to it.
     * @param _title
     * @param _authors
     */
    public void update(String _title, ArrayList<Author> _authors)
    {
        this._title = _title != null ? _title : this._title;
        this._authors = _authors != null ? _authors : this._authors;
    }
}
