package com.library.books.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.books.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, String> {
	 Optional<Borrower> findByEmail(String email);
	 
}
