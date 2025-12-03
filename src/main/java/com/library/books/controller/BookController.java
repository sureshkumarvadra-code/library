package com.library.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.books.model.Book;
import com.library.books.service.BookService;

@RestController
@RequestMapping("/api/books") 
public class BookController {
	
	@Autowired
    private BookService bookService;
	
	  @PostMapping
	    public ResponseEntity<Book> addBook(@RequestBody Book book) {
	        return ResponseEntity.ok(bookService.addBook(book));
	    }
	
	  // List all books
	    @GetMapping("/listOfBooks")
	    public ResponseEntity<List<Book>> getAllBooks() {
	        return ResponseEntity.ok(bookService.getAllBooks());

	    }
}
