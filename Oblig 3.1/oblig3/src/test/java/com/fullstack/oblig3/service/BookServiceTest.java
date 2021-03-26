package com.fullstack.oblig3.service;

import com.fullstack.oblig3.model.Author;
import com.fullstack.oblig3.model.Book;
import com.fullstack.oblig3.repo.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest
{
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepo bookRepo;

    @BeforeEach
    public void setup()
    {
        Book book = new Book(1234l, "Mock book", null), book1 = new Book(1235l, "Mock book 2", null);
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);

        ArgumentCaptor<Long> argumentCaptorLong = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Book> argumentCaptorBook = ArgumentCaptor.forClass(Book.class);
        ArgumentCaptor<String> argumentCaptorString = ArgumentCaptor.forClass(String.class);

        Mockito.lenient()
                .when(bookRepo.getBook(argumentCaptorLong.capture()))
                .thenReturn(book);
        Mockito.lenient()
                .when(bookRepo.getAllBooks())
                .thenReturn(books);
        Mockito.lenient()
                .doNothing()
                .when(bookRepo).createBook(argumentCaptorBook.capture());
        Mockito.lenient()
                .doNothing()
                .when(bookRepo).updateBook(argumentCaptorLong.capture(), argumentCaptorBook.capture());
        Mockito.lenient()
                .doNothing()
                .when(bookRepo).deleteBook(argumentCaptorLong.capture());
        Mockito.lenient()
                .when(bookRepo.searchBookByTitle(argumentCaptorString.capture()))
                .thenReturn(books);
    }

    @Test
    public void getAllBooks()
    {
        List<Book> bookList = bookRepo.getAllBooks();
        assertThat(bookList.get(0).get_ID()).isEqualTo(1234l);
        assertThat(bookList.get(0).get_title()).isEqualTo("Mock book");
        assertThat(bookList.get(0).get_authors()).isEqualTo(null);

        assertThat(bookList.get(1).get_ID()).isEqualTo(1235l);
        assertThat(bookList.get(1).get_title()).isEqualTo("Mock book 2");
        assertThat(bookList.get(1).get_authors()).isEqualTo(null);
    }

    @Test
    public void getOneBook()
    {
        Book book = bookService.getBook(1l);

        assertThat(book.get_ID()).isEqualTo(1234l);
        assertThat(book.get_title()).isEqualTo("Mock book");
        assertThat(book.get_authors()).isEqualTo(null);
    }

    @Test
    public void createBook(@Mock Book book)
    {
        bookService.createBook(book);
    }

    @Test
    public void updateBook(@Mock Book book)
    {
        bookService.updateBook(1l, book);
    }

    @Test
    public void delete()
    {
        bookService.deleteBook(1l);
    }

    @Test
    public void searchBookByTitle()
    {
        List<Book> bookList = bookService.searchBookByTitle("kake");

        assertThat(bookList.get(0).get_ID()).isEqualTo(1234l);
        assertThat(bookList.get(0).get_title()).isEqualTo("Mock book");
        assertThat(bookList.get(0).get_authors()).isEqualTo(null);

        assertThat(bookList.get(1).get_ID()).isEqualTo(1235l);
        assertThat(bookList.get(1).get_title()).isEqualTo("Mock book 2");
        assertThat(bookList.get(1).get_authors()).isEqualTo(null);
    }
}
