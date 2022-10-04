package com.college.library.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.college.library.dao.TransactionRepository;
import com.college.library.entity.Student;
import com.college.library.entity.Transaction;
import com.college.library.service.BookService;
import com.college.library.service.TransactionService;
import com.college.library.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	private BookService bookService;

	@Autowired
	public TransactionServiceImpl(TransactionRepository theTransactionRepository, BookService theBookService,UserService theUserService) {
		transactionRepository = theTransactionRepository;
		bookService = theBookService;
	}

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	// DONT FORGET TO LOOKUP HOW TO HANDLE EXCEPTIONS
	@Override
	public Transaction findOne(int id) {
		Optional<Transaction> transaction = transactionRepository.findById(id);
		if (transaction.isPresent())
			return transaction.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found");
	}
	@Override
	public Transaction findTransactionToReturnBook(Transaction transactionParam) {
		Transaction transaction = transactionRepository.findByBookItemAndBorrowerAndReturned(transactionParam.getBookItem(),transactionParam.getBorrower(),transactionParam.isReturned());
		return transaction;
	}
	
	@Override
	public List<Transaction> findByBorrower(int borrowerId) {
		Student borrower = new Student();
		borrower.setUid(borrowerId);
		List<Transaction> transactions = transactionRepository.findByBorrower(borrower);
		if (!transactions.isEmpty())
			return transactions;
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found");
	}
	

	@Override
	public void issueBook(Transaction transaction) {
		if (transaction.getBook().isAvailable()) {
		transactionRepository.save(transaction);
		bookService.checkAndUpdateBookAvailability(transaction.getBook().getIsbn());
		}
		else 
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The book is not available at the moment");
	}
	
	@Override
	public void returnBook(Transaction transaction) {
		if (!transaction.isReturned()) {
			transaction.setReturned(true);
			transaction.setReturnDate(new Date());
			transactionRepository.save(transaction);
			bookService.checkAndUpdateBookAvailability(transaction.getBook().getIsbn());
		}
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This book is not currently in possession of this student");
	}
	

	@Override
	public void delete(int id) {
		if (transactionRepository.findById(id).get()==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found");
		transactionRepository.deleteById(id);
		bookService.checkAndUpdateBookAvailability(transactionRepository.findById(id).get().getBook().getIsbn());
	}

}
