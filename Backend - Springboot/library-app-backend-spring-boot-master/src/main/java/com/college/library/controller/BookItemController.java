package com.college.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.library.entity.BookItem;
import com.college.library.service.BookItemService;
import com.college.library.service.BookService;

@RestController
@RequestMapping("/api/bookItem")
@CrossOrigin
public class BookItemController {
	
	private BookItemService bookItemService;
	private BookService bookService;
	
	@Autowired
	public BookItemController(BookItemService bookItemService, BookService theBookService) {
		this.bookItemService = bookItemService;
		this.bookService = theBookService; 
	}

	@GetMapping("/all")
	public List<BookItem> findAll() {
		return bookItemService.findAll();
	}

	@GetMapping("/{bookItemId}")
	public BookItem getBookItem(@PathVariable int bookItemId) {

		BookItem bookItem = bookItemService.findOne(bookItemId);

		if (bookItem == null)
			throw new RuntimeException("BookItem barcode not found - " + bookItemId);

		return bookItem;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping
	public BookItem addBookItem(@RequestBody BookItem bookItem) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		bookItemService.save(bookItem);
		bookService.checkAndUpdateBookAvailability(bookItem.getBook().getIsbn());
		return bookItem;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping
	public BookItem updateBookItem(@RequestBody BookItem bookItem) {

		bookItemService.save(bookItem);
		bookService.checkAndUpdateBookAvailability(bookItem.getBook().getIsbn());
		return bookItem;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping
	public String deleteBookItem(@PathVariable int bookItemId) {

		BookItem bookItem = bookItemService.findOne(bookItemId);
		
		// throw exception if null

		if (bookItem == null)
			throw new RuntimeException("BookItem barcode not found - " + bookItemId);

		bookItemService.delete(bookItemId);
		bookService.checkAndUpdateBookAvailability(bookItem.getBook().getIsbn());
		return "Deleted bookItem barcode - " + bookItemId;
	}

}
