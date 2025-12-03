package com.library.books.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.library.books.globalException.ResourceNotFoundException;
import com.library.books.model.Book;
import com.library.books.model.BorrowBook;
import com.library.books.model.Borrower;
import com.library.books.repository.BookRepository;
import com.library.books.repository.BorrowBookRepository;
import com.library.books.repository.BorrowerRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowBookService {
	 @Autowired
	    private BorrowerRepository borrowerRepository;

	    @Autowired
	    private BookRepository bookRepository;

	    @Autowired
	    private BorrowBookRepository borrowBookRepository;

	@Transactional
	public BorrowBook borrowBook(String borrowerId, String bookId) {
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new NoSuchElementException("Borrower not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        if (book.isBorrowed()) {
            throw new ResourceNotFoundException(HttpStatus.CONFLICT,"Book is already borrowed");
        }

        BorrowBook borrowedBook = new BorrowBook();
        borrowedBook.setBorrower(borrower);
        borrowedBook.setBook(book);
        borrowedBook.setBorrowDate(LocalDate.now());
        book.setBorrowed(true);

        bookRepository.save(book);
        return borrowBookRepository.save(borrowedBook);
    }

	@Transactional
	public BorrowBook returnBook(String bookId) {
        BorrowBook borrowedBook = borrowBookRepository.findByBook_IdAndReturnDateIsNull(bookId)
                .orElseThrow(() -> new IllegalStateException("Book is not currently borrowed"));

        borrowedBook.setReturnDate(LocalDate.now());
        Book book = borrowedBook.getBook();
        book.setBorrowed(false);
        bookRepository.save(book);

        return borrowBookRepository.save(borrowedBook);
    }
}