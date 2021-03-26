package com.fullstack.oblig3.web;

import com.fullstack.oblig3.Oblig3Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) // JUnit
@SpringBootTest(webEnvironment = MOCK, classes = Oblig3Application.class) // Spring
@AutoConfigureMockMvc
public class BookControllerTest
{
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllBooksStatusCode() throws Exception
    {
        mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0)))); //Sjekker at vi får en liste med Authors
    }

    @Test
    public void getOneBook() throws Exception
    {
        mockMvc.perform(get("/books/{id}", 6).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._ID").value(6)); //sjekker at id'en på objectet er bevart og hentet ut
    }

    @Test
    public void createBook() throws Exception
    {
        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content("{\"_ID\": 80, \"_title\": \"Kake\", \"_authors\": []}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateBook() throws Exception
    {
        mockMvc.perform(put("/books/{id}", 12).contentType(MediaType.APPLICATION_JSON)
                .content("{\"_title\": \"Banan\", \"_authors\": [{\"_ID\": 44}]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteBook() throws Exception
    {
        mockMvc.perform(delete("/books/{id}", 13).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void searchBookByTitle() throws Exception
    {
        mockMvc.perform(get("/books/search/{title}", "kake").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0)))); //Sjekker at vi får en liste med Authors
    }

}
