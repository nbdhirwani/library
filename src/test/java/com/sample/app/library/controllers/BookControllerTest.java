package com.sample.app.library.controllers;

import com.sample.app.library.dtos.book.BookDTO;
import com.sample.app.library.dtos.book.BookPayload;
import com.sample.app.library.repositories.entity.Book;
import com.sample.app.library.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Type;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void whenGetEndpointIsInvokedForAllBooks_thenReturns200OK() throws Exception {
        final UUID uuid1 = UUID.randomUUID();
        final UUID uuid2 = UUID.randomUUID();

        final Book book1 = new Book(uuid1, "name1", "authorName1", Boolean.FALSE);
        final Book book2 = new Book(uuid2, "name2", "authorName2", Boolean.FALSE);

        final BookDTO book1DTO = new BookDTO(uuid1, "name1", "authorName1", Boolean.FALSE);
        final BookDTO book2DTO = new BookDTO(uuid2, "name2", "authorName2", Boolean.FALSE);

        when(bookService.findBooks(any(Sort.class)))
                .thenReturn(asList(book1, book2));
        when(modelMapper.map(any(), any(Type.class)))
                .thenReturn(asList(book1DTO, book2DTO));

        mockMvc.perform(
                        get("/api/books")
                            .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[?(@.name == \"name1\" && @.authorName == \"authorName1\")]").exists())
                .andExpect(jsonPath("$[?(@.name == \"name2\" && @.authorName == \"authorName2\")]").exists());
    }

    @Test
    public void whenGetEndpointIsInvokedForASingleBook_thenReturns200OK() throws Exception {
        final UUID uuid = UUID.randomUUID();

        final Book book = new Book(uuid, "name", "authorName", Boolean.FALSE);
        final BookDTO bookDTO = new BookDTO(uuid, "name", "authorName", Boolean.FALSE);

        when(bookService.getBook(any(UUID.class)))
                .thenReturn(book);
        when(modelMapper.map(any(Book.class), any()))
                .thenReturn(bookDTO);

        mockMvc.perform(
                get("/api/book/{id}", uuid.toString())
                        .contentType("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.authorName").value("authorName"))
                .andExpect(jsonPath("$.isBorrowed").value(Boolean.FALSE));
    }

    @Test
    public void whenPostEndpointIsInvoked_thenReturns200OK() throws Exception {
        final UUID uuid = UUID.randomUUID();

        final Book book = new Book(uuid, "name", "authorName", Boolean.FALSE);
        final BookDTO bookDTO = new BookDTO(uuid, "name", "authorName", Boolean.FALSE);

        when(modelMapper.map(any(BookPayload.class), eq(Book.class)))
                .thenReturn(book);
        when(bookService.createBook(any(Book.class)))
                .thenReturn(book);
        when(modelMapper.map(any(Book.class), eq(BookDTO.class)))
                .thenReturn(bookDTO);

        mockMvc.perform(
                post("/api/book")
                .contentType("application/json")
                .content("""
                        {
                            "name": "bookName",
                            "authorName" : "authorName",
                            "isBorrowed": false
                        }
                        """)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.authorName").value("authorName"))
                .andExpect(jsonPath("$.isBorrowed").value(Boolean.FALSE));
    }

    @Test
    public void whenPutEndpointIsInvoked_thenReturns200OK() throws Exception {

        final UUID uuid = UUID.randomUUID();

        final Book book = new Book(uuid, "name", "authorName", Boolean.FALSE);
        final BookDTO bookDTO = new BookDTO(uuid, "name", "authorName", Boolean.FALSE);

        when(modelMapper.map(any(BookPayload.class), eq(Book.class)))
                .thenReturn(book);
        when(bookService.updateBook(any(UUID.class), any(Book.class)))
                .thenReturn(book);
        when(modelMapper.map(any(Book.class), eq(BookDTO.class)))
                .thenReturn(bookDTO);

        mockMvc.perform(
                put("/api/book/{id}", uuid.toString())
                        .contentType("application/json")
                        .content("""
                                {
                                    "name": "bookName",
                                    "authorName" : "authorName"
                                }
                                """)
        ).andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value(uuid.toString()))
        .andExpect(jsonPath("$.name").value("name"))
        .andExpect(jsonPath("$.authorName").value("authorName"))
        .andExpect(jsonPath("$.isBorrowed").value(Boolean.FALSE));
    }

    @Test
    public void whenDeleteEndpointIsInvoked_thenReturns200OK() throws Exception {
        doNothing().when(bookService).deleteBook(any(UUID.class));
        final UUID uuid = UUID.randomUUID();
        mockMvc.perform(
                delete("/api/book/{id}", uuid.toString())
                        .contentType("application/json")
        ).andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(uuid);
        verifyNoMoreInteractions(bookService);
    }
}