package com.college.library.service;

import java.util.List;

import com.college.library.entity.Transaction;

public interface TransactionService {
	public List<Transaction> findAll();

	public Transaction findOne(int id);

	public void delete(int id);

	List<Transaction> findByBorrower(int borrowerId);
	
	void issueBook(Transaction transaction);
	
	void returnBook(Transaction transaction);

	Transaction findTransactionToReturnBook(Transaction transactionParam);

}
