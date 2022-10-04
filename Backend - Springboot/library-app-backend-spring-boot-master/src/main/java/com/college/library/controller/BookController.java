package com.college.library.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.college.library.entity.Book;
import com.college.library.entity.Category;
import com.college.library.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("/api/book")
public class BookController {

	private BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	// expose "/books" and return list of books
	@GetMapping("/all")
	public List<Book> findAll() {
		return bookService.findAll();
	}
	
	@GetMapping("/path")
	public String findPath() {
//		return System.getProperty("user.dir")+"/";
		Path p = Paths.get("C:/xampp/htdocs/img");
		return p.toString();
	}
		
	@GetMapping("/available/{bookId}")
	public int checkBookAvailability(@PathVariable int bookId) {
		return bookService.checkAndUpdateBookAvailability(bookId);
	}

	// add mapping for GET /employees/{bookId}
	// EXCEEEEEEEEEEEEEEPTIIIIIIIIIIIIION
	@GetMapping("/{bookId}")
	public Book getBook(@PathVariable int bookId) {

		Book book = bookService.findOne(bookId);
		/*if (book == null)
			throw new RuntimeException("Book isbn not found - " + bookId);*/
		return book;
	}
	
	@GetMapping("/category/{category}")
	public List<Book> getBookByCategory(@PathVariable String category){
		Category categoryEnum = Category.valueOf(category);
		List<Book> books = bookService.findBookByCategory(categoryEnum);
		return books;
	}

	// add mapping for POST /employees - add new employee

/*	@PostMapping
	public Book addBook(@RequestBody Book book) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update // CHECK IF U CAN
		// REMOVE setID(0)
		//book.setIsbn(0);
		bookService.save(book);

		return book;
	}*/
	@PostMapping
	public Book addBook(@RequestBody Book book) throws Exception {
		bookService.save(book);
		return book;
	}
	
	@PostMapping("/image")
	public boolean addImage(@RequestParam("file") MultipartFile image) {
		
		try {
			bookService.saveImage(image);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
		return true;
	}
	// add mapping for PUT /employees - update existing employee

	@PutMapping
	public Book updateBook(@RequestBody Book book) {

		bookService.save(book);

		return book;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping
	public String deleteBook(@PathVariable int bookId) {

		Book book = bookService.findOne(bookId);

		// throw exception if null

		if (book == null) {
			throw new RuntimeException("Book ISBN not found - " + bookId);
		}

		bookService.delete(bookId);

		return "Deleted book ISBN - " + bookId;
	}

}
