package com.sample.app.library.services;

import com.sample.app.library.repositories.BookRepository;
import com.sample.app.library.repositories.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void findBooksInvokesRepositoryMethod() {
        when(bookRepository.findAll(any(Sort.class)))
                .thenReturn(Collections.emptyList());

        final Sort sort = Sort.by(Sort.Direction.ASC, "name");
        final List<Book> books = bookService.findBooks(sort);

        assertThat(books, is(empty()));

        verify(bookRepository, times(1)).findAll(sort);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void deleteBookInvokesRepositoryMethod() {
        doNothing().when(bookRepository).deleteById(any(UUID.class));

        final UUID uuid = UUID.randomUUID();
        bookService.deleteBook(uuid);

        verify(bookRepository, times(1)).deleteById(uuid);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void getBookInvokesRepositoryMethod() {
        final UUID uuid = UUID.randomUUID();
        final Book mockBook = new Book(uuid, "name", "authorName", Boolean.FALSE);
        when(bookRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(mockBook));

        final Book book = bookService.getBook(uuid);

        assertThat(book, is(mockBook));

        verify(bookRepository, times(1)).findById(uuid);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void getBookGeneratesExceptionWhenRepositoryMethodReturnsEmptyOptional() {
        when(bookRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());
        final UUID uuid = UUID.randomUUID();

        assertThrows(RuntimeException.class, () -> {
            final Book book = bookService.getBook(uuid);
        });

        verify(bookRepository, times(1)).findById(uuid);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void updateBookInvokesRepositoryMethod() {
        final UUID uuid = UUID.randomUUID();
        final Book mockOldBook = new Book(uuid, "name", "authorName", Boolean.FALSE);
        final Book newBook = new Book(uuid, "nameNew", "authorNameNew", Boolean.FALSE);
        when(bookRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(mockOldBook));
        when(bookRepository.save(any(Book.class)))
                .thenReturn(newBook);

        final Book book = bookService.updateBook(uuid, newBook);

        assertThat(book, is(newBook));

        verify(bookRepository, times(1)).findById(uuid);
        verify(bookRepository, times(1)).save(newBook);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void updateBookGeneratesExceptionWhenRepositoryMethodReturnsEmptyOptional() {
        when(bookRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());
        final UUID uuid = UUID.randomUUID();

        assertThrows(RuntimeException.class, () -> {
            final Book newBook = new Book(uuid, "nameNew", "authorNameNew", Boolean.FALSE);
            final Book book = bookService.updateBook(uuid, newBook);
        });

        verify(bookRepository, times(1)).findById(uuid);
        verifyNoMoreInteractions(bookRepository);
    }
}