package com.booklify.service;

import com.booklify.domain.Book;
import com.booklify.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepo;

    @Override
    public Book save(Book entity) {
        return bookRepo.save(entity);
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> optional = bookRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Book update(Book entity) {
        return bookRepo.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        if (bookRepo.existsById(aLong)) {
            bookRepo.deleteById(aLong);
        }
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public void deleteAll() {
        bookRepo.deleteAll();
    }
}
