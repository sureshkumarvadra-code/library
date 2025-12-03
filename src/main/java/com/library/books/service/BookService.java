package com.library.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.library.books.globalException.ResourceNotFoundException;
import com.library.books.model.Book;
import com.library.books.repository.BookRepository;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository; 
    
    // Register a new book
    @Transactional
    public Book addBook(Book book) { 

        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
        	logger.debug("ISBN is required");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"ISBN is required");
        }
        if (book.getTitle() == null || book.getTitle().isBlank()) {
        	logger.debug("Title is required");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
        	logger.debug("Author is required");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Author is required");
        }
    	logger.debug("book.getIsbn()" +book.getIsbn());

        List<Book> bookExist = bookRepository.findByIsbn(book.getIsbn());
        
        if (!bookExist.isEmpty()) {
        	logger.debug("book List" +bookExist);
            for (Book b : bookExist) {
            	logger.debug("book.getTitle()" +book.getTitle());
            	logger.debug("book.getAuthor()" +book.getAuthor());


                if (!b.getTitle().equals(book.getTitle()) || !b.getAuthor().equals(book.getAuthor())) {
                    throw new ResourceNotFoundException(
                      HttpStatus.IM_USED,  "Book with same ISBN already exists with different title or author"
                    );
                }
            }
        }
        System.out.println("Before save: " + book.getId()); // likely null

        Book savedBook = bookRepository.save(book);
        System.out.println("Book ID after save: " + savedBook.getId()); // UUID generated

        bookRepository.flush(); 
        return savedBook;
    }
    
    // Get list of all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


}
