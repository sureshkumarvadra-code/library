package com.library.books.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.books.model.BorrowBook;

public interface BorrowBookRepository extends JpaRepository<BorrowBook, String> {
    Optional<BorrowBook> findByBook_IdAndReturnDateIsNull(String bookId);
}
