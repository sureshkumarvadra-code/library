package com.library.books.model;

public class BorrowRequest {
	 private String borrowerId;
	    private String bookId;
		public String getBorrowerId() {
			return borrowerId;
		}
		public void setBorrowerId(String borrowerId) {
			this.borrowerId = borrowerId;
		}
		public String getBookId() {
			return bookId;
		}
		public void setBookId(String bookId) {
			this.bookId = bookId;
		}
	    
}
