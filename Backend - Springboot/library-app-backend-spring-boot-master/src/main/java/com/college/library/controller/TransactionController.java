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
import com.college.library.entity.Student;
import com.college.library.entity.Transaction;
import com.college.library.service.BookItemService;
import com.college.library.service.TransactionService;
import com.college.library.service.UserService;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin
public class TransactionController {

	private TransactionService transactionService;
	private UserService userService;
	private BookItemService bookItemService;

	@Autowired
	public TransactionController(TransactionService transactionService, UserService theUserService,BookItemService theBookItemService) {
		this.transactionService = transactionService;
		this.userService = theUserService;
		this.bookItemService = theBookItemService;
	}

	@GetMapping("/all")
	public List<Transaction> findAll() {
		return transactionService.findAll();
	}
	

	@GetMapping("/{transactionId}")
	public Transaction getTransaction(@PathVariable int transactionId) {

		Transaction transaction = transactionService.findOne(transactionId);

		if (transaction == null)
			throw new RuntimeException("transaction id not found - " + transactionId);

		return transaction;
	}
	
	@GetMapping("/student/{studentId}")
	public List<Transaction> getTransactionByBorrower(@PathVariable int studentId) {

		List<Transaction> transactions = transactionService.findByBorrower(studentId);

		if (transactions == null)
			throw new RuntimeException("transaction by student not found - " + studentId);

		return transactions;
	}

	// add mapping for POST /employees - add new employee

	@GetMapping("/{studentId}/{bookItemBarcode}")
	public Transaction findbyBookItemAndStudent(@PathVariable int studentId,@PathVariable int bookItemBarcode) {
		
		Student student = (Student) this.userService.findOne(studentId);
		BookItem bookItem = this.bookItemService.findOne(bookItemBarcode);
		Transaction transaction = new Transaction();
		transaction.setBorrower(student);
		transaction.setBookItem(bookItem);
		transaction.setReturned(false);
		transaction = this.transactionService.findTransactionToReturnBook(transaction);
		if (transaction == null)
			throw new RuntimeException("transaction not found - ");
		return transaction;
	}
	
	@PostMapping
	public Transaction addTransaction(@RequestBody Transaction transaction) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		transaction.setId(0);

		transactionService.issueBook(transaction);
		

		return transaction;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping
	public Transaction updateTransaction(@RequestBody Transaction transaction) {
		transactionService.returnBook(transaction);
		return transaction;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping
	public String deleteTransaction(@PathVariable int transactionId) {

		Transaction transaction = transactionService.findOne(transactionId);

		// throw exception if null

		if (transaction == null)
			throw new RuntimeException("Transaction id not found - " + transactionId);

		transactionService.delete(transactionId);

		return "Deleted transaction id - " + transactionId;
	}

}
