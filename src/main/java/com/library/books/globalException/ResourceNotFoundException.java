package com.library.books.globalException;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(HttpStatus conflict, String message) {
        super(message);
    }
}
