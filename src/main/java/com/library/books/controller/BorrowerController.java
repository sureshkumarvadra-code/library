package com.library.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.books.model.Borrower;
import com.library.books.service.BorrowerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
	 @Autowired
	    private BorrowerService borrowerService;

	    @PostMapping
	    public ResponseEntity<Borrower> registerBorrower(@Valid @RequestBody Borrower borrower) {
	        return ResponseEntity.ok(borrowerService.registerBorrower(borrower));
	    }
}
