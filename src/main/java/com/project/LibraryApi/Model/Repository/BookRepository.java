package com.project.LibraryApi.Model.Repository;

import com.project.LibraryApi.Model.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

}
