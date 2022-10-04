package com.college.library.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.college.library.dao.BookRepository;
import com.college.library.dao.TransactionRepository;
import com.college.library.entity.Book;
import com.college.library.entity.Category;
import com.college.library.entity.Transaction;
import com.college.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	private final Path rootLocation = Paths.get("C:/xampp/htdocs/img");
	private BookRepository bookRepository;
	private TransactionRepository transactionRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository theBookRepository, TransactionRepository theTransactionRepository) {
		bookRepository = theBookRepository;
		transactionRepository = theTransactionRepository;
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	// DONT FORGET TO LOOKUP HOW TO HANDLE EXCEPTIONS
	@Override
	public Book findOne(int isbn) {
		Optional<Book> book = bookRepository.findById(isbn);
		if (book.isPresent())
			return book.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
	}
	
	@Override
	public List<Book> findBookByCategory(Category category){
		List<Book> books = bookRepository.findByCategory(category);
		return books;
	}
	
	@Override
	public int checkAndUpdateBookAvailability(int isbn) {
		Book book = this.findOne(isbn);
		int bookItemsNumber = book.getBookItems().size();
		int bookIssued=0;
		List<Transaction> transaction = transactionRepository.findByBook(book);
		for (Transaction transactionTemp : transaction) {
			if (!transactionTemp.isReturned())
				bookIssued++;
		}
		if (bookIssued<bookItemsNumber)
		{
			if (!book.isAvailable()){
				book.setAvailable(true);
				this.save(book);
				}
		}
		else {
			if (book.isAvailable())
			{
				book.setAvailable(false);
				this.save(book);
			}
		}
		return bookItemsNumber-bookIssued;
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);
		//book.getBookItems( ) ==0
	}
	
	@Override
	public void saveImage(MultipartFile image) throws IOException {
		
		Files.copy(image.getInputStream(), this.rootLocation.resolve(image.getOriginalFilename()));

	}

	@Override
	public void delete(int isbn) {
		if (bookRepository.findById(isbn).get()==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
		bookRepository.deleteById(isbn);
	}
}
