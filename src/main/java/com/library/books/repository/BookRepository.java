package com.library.books.repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.books.model.Book;
public interface BookRepository extends JpaRepository<Book, String> {
	
    List<Book> findByIsbn(String isbn);
    List<Book> findByTitleContainingIgnoreCase(String keyword);
}
