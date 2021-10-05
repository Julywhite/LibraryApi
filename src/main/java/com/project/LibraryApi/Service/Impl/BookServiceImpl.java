package com.project.LibraryApi.Service.Impl;

import com.project.LibraryApi.Model.Entity.Book;
import com.project.LibraryApi.Model.Repository.BookRepository;
import com.project.LibraryApi.Service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl( BookRepository repository ) {
        this.repository = repository;
    }

    @Override
    public Book save( Book book ) {
        return repository.save( book );
    }
}
