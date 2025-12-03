package com.library.books.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalException {
	
	  
    
    // Handle custom exceptions (like BookNotFoundException)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<LibraryCommonException> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new LibraryCommonException(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }
    
    
}
