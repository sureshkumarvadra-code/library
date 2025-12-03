package com.library.books.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.books.model.BorrowBook;
import com.library.books.model.BorrowRequest;
import com.library.books.service.BorrowBookService;

@RestController
@RequestMapping("/api/borrow")
public class BorrowBookController {
	 @Autowired
	    private BorrowBookService borrowBookService;

	    @PostMapping
	    public ResponseEntity<BorrowBook> borrowBook(@RequestBody BorrowRequest borrowRequest) {
	    	   
	        return ResponseEntity.ok(borrowBookService.borrowBook(
	                borrowRequest.getBorrowerId(),
	                borrowRequest.getBookId()));
	    
	    }

	    @PostMapping("/return")
	    public ResponseEntity<BorrowBook> returnBook(@RequestBody BorrowRequest borrowRequest) {
	        BorrowBook returnedBook = borrowBookService.returnBook(
	            borrowRequest.getBookId()  // only bookId is needed
	        );
	        return ResponseEntity.ok(returnedBook);
	    }
	   
}
