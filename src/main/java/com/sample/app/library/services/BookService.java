package com.sample.app.library.services;

import com.sample.app.library.repositories.BookRepository;
import com.sample.app.library.repositories.entity.Book;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findBooks(final Sort sort) {
        return bookRepository.findAll(sort);
    }

    public Book createBook(final Book book) {
        book.setId(UUID.randomUUID());
        book.setIsBorrowed(Boolean.FALSE);
        return bookRepository.save(book);
    }

    public void deleteBook(final UUID id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(final UUID id, final Book newBook) throws RuntimeException {
        final Book book = getBook(id);
        book.setName(ObjectUtils.defaultIfNull(newBook.getName(), book.getName()));
        book.setAuthorName(ObjectUtils.defaultIfNull(newBook.getAuthorName(), book.getAuthorName()));
        book.setIsBorrowed(ObjectUtils.defaultIfNull(newBook.getIsBorrowed(), book.getIsBorrowed()));
        return bookRepository.save(book);
    }

    public Book getBook(final UUID id) throws RuntimeException {
        return bookRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
