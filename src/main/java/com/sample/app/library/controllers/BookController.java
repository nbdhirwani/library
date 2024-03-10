package com.sample.app.library.controllers;

import com.sample.app.library.dtos.book.BookDTO;
import com.sample.app.library.dtos.book.BookPayload;
import com.sample.app.library.repositories.entity.Book;
import com.sample.app.library.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> getBooks(@RequestParam(defaultValue = "id") final String sortField,
                                  @RequestParam(defaultValue = "ASC") final Sort.Direction sortDirection) {
        final Sort sort = Sort.by(sortDirection, sortField);
        final List<Book> books = bookService.findBooks(sort);
        List<BookDTO> bookDTOS = modelMapper.map(books, new TypeToken<List<BookDTO>>() {
        }.getType());
        return bookDTOS;
    }

    @GetMapping(path = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDTO getBook(@PathVariable final UUID id) {
        final Book book = bookService.getBook(id);
        return modelMapper.map(book, BookDTO.class);
    }

    @PostMapping(path ="/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDTO createBook(@RequestBody final BookPayload bookPayload) {
        final Book book = modelMapper.map(bookPayload, Book.class);
        final Book savedBook = bookService.createBook(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    @PutMapping(path = "/book/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDTO updateBook(@PathVariable final UUID id, @RequestBody final BookPayload bookPayload) {
        final Book newBook = modelMapper.map(bookPayload, Book.class);
        final Book book = bookService.updateBook(id, newBook);
        return modelMapper.map(book, BookDTO.class);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable final UUID id) {
        bookService.deleteBook(id);
    }
}
