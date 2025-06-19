package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Collection<Book> listBooks() {
        return repository.findAll();
    }

    public Book getBook(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    public Book createBook(Book book) {
        book.setId(null);
        return repository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        if (!repository.findById(id).isPresent()) {
            throw new NoSuchElementException("Book not found");
        }
        book.setId(id);
        return repository.save(book);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
