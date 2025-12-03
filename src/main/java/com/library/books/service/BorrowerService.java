package com.library.books.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.library.books.globalException.ResourceNotFoundException;
import com.library.books.model.Borrower;
import com.library.books.repository.BorrowerRepository;
import java.util.NoSuchElementException;
import jakarta.transaction.Transactional;

@Service
public class BorrowerService {
    private static final Logger logger = LoggerFactory.getLogger(BorrowerService.class);

    @Autowired
    private BorrowerRepository borrowerRepository;


    @Transactional
    public Borrower registerBorrower(Borrower borrower) {
        if (borrowerRepository.findByEmail(borrower.getEmail()).isPresent()) {
            throw new ResourceNotFoundException(HttpStatus.IM_USED,"Email already exists");
        }
        return borrowerRepository.save(borrower);
    }
    
    public Borrower getBorrowerById(String id) {
        return borrowerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Borrower not found"));
    }
    
}
